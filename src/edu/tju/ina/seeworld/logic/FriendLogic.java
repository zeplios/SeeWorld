package edu.tju.ina.seeworld.logic;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.tju.ina.seeworld.dao.user.IFriendDAO;
import edu.tju.ina.seeworld.dao.user.IRequestDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Friend;
import edu.tju.ina.seeworld.po.user.Request;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.vo.FriendVO;

/**
 * 对于mutual
 * friends的处理，鉴于查找的动作要远远多于增删，故而我们采用在save的时候交换offer,theOther，进行两次存储，删除时亦然
 * ,从而保证在查找mutual firends的时候，只需一次查找。
 * 
 * @author Uranus
 * 
 */
public class FriendLogic implements IFriendLogic {

	private IFriendDAO friendDao;
	private IRequestDAO requestDao;
	private IUserDAO userDao;
	private JSONObject friendJson;

	public JSONObject getFriendJson() {
		return friendJson;
	}

	public int getFriendSum(String userId) throws SeeWorldException {
		HashMap<String, Object> params = new HashMap<String, Object>(1);
		params.put(IFriendDAO.OFFER, userId);
		return friendDao.getCount(params);
	}

	public void setFriendDao(IFriendDAO friendDao) {
		this.friendDao = friendDao;
	}

	public void setRequestDao(IRequestDAO requestDao) {
		this.requestDao = requestDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public boolean areFriends(FriendVO friend) throws SeeWorldException {
		String offerId = friend.getOfferId();
		String theOtherId = friend.getTheOtherId();
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(IFriendDAO.OFFER, offerId);
		params.put(IFriendDAO.THEOTHER, theOtherId);
		return friendDao.getCount(params) > 0;
	}

	public void endFriendship(FriendVO friend) throws SeeWorldException {
		String offerId = friend.getOfferId();
		String theOtherId = friend.getTheOtherId();
		User offer = userDao.findById(offerId);
		User theOther = userDao.findById(theOtherId);
		List<Friend> list = friendDao.findByOfferAndTheOther(offer, theOther);
		if (list.size() > 0) {
			friendDao.delete(list.get(0));
		}
		list = friendDao.findByOfferAndTheOther(theOther, offer);
		if (list.size() > 0) {
			friendDao.delete(list.get(0));
		}
	}

	public void makeFriends(FriendVO friend) throws SeeWorldException {
		String offerId = friend.getOfferId();
		String theOtherId = friend.getTheOtherId();
		User offer = userDao.findById(offerId);
		User theOther = userDao.findById(theOtherId);
		if (!areFriends(friend)) {
			Friend instance1 = new Friend();
			instance1.setUserOffer(offer);
			instance1.setUserTheOther(theOther);
			friend.setId((Integer) friendDao.save(instance1));
			Friend instance2 = new Friend();
			instance2.setUserOffer(theOther);
			instance2.setUserTheOther(offer);
			friendDao.save(instance2);
		}
		List<Request> list = requestDao.findByTwoProperty(IRequestDAO.SENDER,
				offer, IRequestDAO.RECEIVER, theOther);
		Request request = list.get(0);
		requestDao.delete(request);
		friendJson = JSONObject.fromObject(friend);
	}

	public JSONArray findFriendsByPage(FriendVO friend, int offset,
			int pageSize) throws SeeWorldException {
		String offerId = friend.getOfferId();
		String hql = "from Friend as model where model.userOffer='" + offerId
				+ "' order by model.addTime desc";
		List<Friend> allFriendByPage = friendDao.getListByPage(hql, offset,
				pageSize);
		JSONArray friendList = new JSONArray();
		for (Friend f : allFriendByPage) { // VO封装
			FriendVO friendVo = new FriendVO();
			friendVo.setId(f.getId());
			friendVo.setOfferId(f.getUserOffer().getId());
			friendVo.setTheOtherId(f.getUserTheOther().getId());
			friendVo.setTheOtherName(f.getUserTheOther().getRealName());
			friendVo.setTheOtherPhoto(f.getUserOffer().getPhotoPath());
			friendVo.setTheOtherAcademy(f.getUserTheOther().getAcademy()
					.getName());
			friendVo.setTheOtherSpecialty(f.getUserTheOther().getSpecialty()
					.getName());
			friendList.add(friendVo);
		}
		return friendList;
	}
}
