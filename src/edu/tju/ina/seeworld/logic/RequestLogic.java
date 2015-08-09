package edu.tju.ina.seeworld.logic;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.tju.ina.seeworld.dao.user.IRequestDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Request;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.vo.FriendVO;
import edu.tju.ina.seeworld.vo.Pagination;
import edu.tju.ina.seeworld.vo.RequestVO;

public class RequestLogic implements IRequestLogic {
	public static String FRIEND = "friend";
	public static String S_REQUEST = "s_request";
	public static String R_REQUEST = "r_request";
	public static String SUCCESS = "success";
	public static String SELF = "self";
	private IRequestDAO requestDao;
	private IFriendLogic friendLogic;
	private IUserDAO userDao;
	private JSONObject requestJson;
	private JSONArray requestList;
	private int requestSum;
	private JSONObject PageJson;
	private VOPOTransformator vOPOTransformator;
	private SettingLogic settingLogic;
	HashMap<String, Object> params = new HashMap<String, Object>();

	public JSONObject getPageJson() {
		return PageJson;
	}

	public int getRequestSum() {
		return requestSum;
	}

	public JSONArray getRequestList() {
		return requestList;
	}

	public JSONObject getRequestJson() {
		return requestJson;
	}

	public void setFriendLogic(IFriendLogic friendLogic) {
		this.friendLogic = friendLogic;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public void setRequestDao(IRequestDAO requestDao) {
		this.requestDao = requestDao;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public String addRequest(RequestVO request) throws SeeWorldException {
		String senderId = request.getSender_id();
		String recieverId = request.getReciever_id();
		if (senderId.equals(recieverId)) {
			return SELF;
		}
		params.clear();
		params.put(IRequestDAO.RECEIVER, recieverId);
		params.put(IRequestDAO.SENDER, senderId);
		if (requestDao.getCount(params) > 0) {
			return S_REQUEST;
		}
		params.clear();
		params.put(IRequestDAO.SENDER, recieverId);
		params.put(IRequestDAO.RECEIVER, senderId);
		if (requestDao.getCount(params) > 0) {
			return R_REQUEST;
		}
		if (friendLogic.areFriends(new FriendVO(recieverId,senderId))) {
			return FRIEND;
		}
		Request instance = new Request();
		User reciever = userDao.findById(recieverId);
		User sender = userDao.findById(senderId);
		instance.setSender(sender);
		instance.setReciever(reciever);
		requestDao.save(instance);
		RequestVO requestVo = new RequestVO(instance);
		requestJson = JSONObject.fromObject(requestVo);
		return SUCCESS;

	}

	public String deleteRequest(RequestVO request) throws SeeWorldException {
		Request instance = requestDao.findById(request.getId());
		requestDao.delete(instance);
		return SUCCESS;
	}

	public void showRequestList(RequestVO request) throws SeeWorldException {
		String reciever_id = request.getReciever_id();
		User reciever = userDao.findById(reciever_id);
		List<Request> list = requestDao
				.findByProperty(
						"from Request as model where model.reciever=? order by model.addTime desc",
						reciever);
		requestSum = list.size();
		if (requestSum > 0) {
			requestList = new JSONArray();
			requestList.addAll(vOPOTransformator.transferRequestToVOList(list));
		}
	}

	public void gotoPage(RequestVO request, Integer requestSum,
			Integer currentPage) throws SeeWorldException {
		String reciever_id = request.getReciever_id();
		Pagination pager = new Pagination();
		pager.setLen(requestSum);
		pager.setPagesize(settingLogic.getIntConfigValue(Constant.REQUESTS_PER_PAGE));
		pager.setCurrentpage(currentPage);
		pager.setPagelist();
		String hql = "from Request as model where model.reciever='"
				+ reciever_id + "' order by model.addTime desc";
		List<Request> allRequest = requestDao.getListByPage(hql, pager
				.getStart(), pager.getPagesize());
		requestList = new JSONArray();
		requestList.addAll(vOPOTransformator
				.transferRequestToVOList(allRequest));
		PageJson = JSONObject.fromObject(pager);
	}

	public int getRequestNum(RequestVO request) throws SeeWorldException {
		String reciever_id = request.getReciever_id();
		params.clear();
		params.put(IRequestDAO.RECEIVER, reciever_id);
		requestSum = requestDao.getCount(params);
		return requestSum;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
}
