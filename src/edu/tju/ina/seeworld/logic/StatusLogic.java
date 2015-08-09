package edu.tju.ina.seeworld.logic;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.tju.ina.seeworld.dao.user.IStatusDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Status;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.vo.Pagination;
import edu.tju.ina.seeworld.vo.StatusVO;

public class StatusLogic implements IStatusLogic {
	private IStatusDAO statusDao;
	private IUserDAO userDao;
	private JSONObject jsonStatus;
	private JSONArray statusList;
	private int statusSum;
	private JSONObject PageJson;

	public JSONObject getPageJson() {
		return PageJson;
	}

	public int getStatusSum() {
		return statusSum;
	}

	public JSONArray getStatusList() {
		return statusList;
	}

	public JSONObject getJsonStatus() {
		return jsonStatus;
	}

	public void setStatusDao(IStatusDAO statusDao) {
		this.statusDao = statusDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public void delStatus(StatusVO status) throws SeeWorldException {
		Integer Id = Integer.valueOf(status.getId());
		Status instance = statusDao.findById(Id);
		System.out.println(instance.getId());
		statusDao.delete(instance);
	}

	public List<Status> getAllStatus(StatusVO status) throws SeeWorldException {
		String userId = status.getUserId();
		String hql = "from Status as model where model.user='" + userId
				+ "' order by model.addTime desc";
		List<Status> list = statusDao.findByQuery(hql);
		Iterator allStatus = list.iterator();
		statusList = new JSONArray();
		while (allStatus.hasNext()) { // VO封装
			Status instance = (Status) allStatus.next();
			StatusVO statusjson = new StatusVO();
			statusjson.setId(instance.getId());
			statusjson.setUserId(instance.getUser().getId());
			statusjson.setPhotoPath(instance.getUser().getPhotoPath());
			statusjson.setStatus(instance.getStatus());
			statusjson.setAddTime(instance.getAddTime().toString());
			statusList.add(statusjson);
		}
		return list;
	}

	public void updateStatus(StatusVO status) throws SeeWorldException {
		Status instance = new Status();
		Timestamp addTime = new Timestamp(System.currentTimeMillis());
		User user = userDao.findById(status.getUserId());
		instance.setUser(user);
		instance.setStatus(status.getStatus());
		instance.setAddTime(addTime);
		statusDao.save(instance);
		status.setAddTime(changeTime(addTime));
		jsonStatus = JSONObject.fromObject(status);
	}

	public void showStatus(StatusVO status) throws SeeWorldException {
		statusSum = getAllStatus(status).size();
		Status instance = getAllStatus(status).get(0);
		Timestamp addTime = instance.getAddTime();
		StatusVO statusVo = new StatusVO();
		statusVo.setId(instance.getId());
		statusVo.setUserId(instance.getUser().getId());
		statusVo.setStatus(instance.getStatus());
		statusVo.setAddTime(changeTime(addTime));
		jsonStatus = JSONObject.fromObject(statusVo);
	}

	public void gotoPage(Integer statusSum, Integer currentPage, String userId)
			throws SeeWorldException {
		Pagination pager = new Pagination();
		pager.setLen(statusSum);
		pager.setPagesize(2);
		pager.setCurrentpage(currentPage);
		pager.setPagelist();
		String hql = "from Status as model where model.user='" + userId
				+ "' order by model.addTime desc";
		Iterator allStatus = statusDao.getListByPage(hql, pager.getStart(),
				pager.getPagesize()).iterator();
		statusList = new JSONArray();
		while (allStatus.hasNext()) { // VO封装
			Status status = (Status) allStatus.next();
			StatusVO statusVo = new StatusVO();
			statusVo.setId(status.getId());
			statusVo.setStatus(status.getStatus());
			statusVo.setPhotoPath(status.getUser().getPhotoPath());
			Calendar now = Calendar.getInstance();
			Timestamp addTime = status.getAddTime();
			statusVo.setAddTime(status.getAddTime().toString());
			// System.out.println(statusVo.getId());
			statusList.add(statusVo);
		}
		PageJson = JSONObject.fromObject(pager);
	}

	public String changeTime(Timestamp addTime) {
		String date = "";
		Calendar now = Calendar.getInstance();
		if (now.get(Calendar.YEAR) != (addTime.getYear() + 1900)) {
			date = "n天前";
		} else {
			if (now.get(Calendar.MONTH) == addTime.getMonth()) {
				int day = now.get(Calendar.DAY_OF_MONTH) - addTime.getDate();
				if (day < 8 && day > 0) {
					date = String.valueOf(day) + "天前";
				} else if (day == 0) {
					int hour = now.get(Calendar.HOUR_OF_DAY)
							- addTime.getHours();
					int minute = now.get(Calendar.MINUTE)
							- addTime.getMinutes();
					if (hour != 0) {
						if (hour == 1 && minute < 0) {
							int tmp = minute + 60;
							date = String.valueOf(tmp) + "分钟前";
						} else {
							date = String.valueOf(hour) + "小时前";
						}
					} else {
						if (minute > 0) {
							date = String.valueOf(minute) + "分钟前";
						} else {
							date = "刚更新";
						}
					}
				}
			}
		}
		return date;
	}
}
