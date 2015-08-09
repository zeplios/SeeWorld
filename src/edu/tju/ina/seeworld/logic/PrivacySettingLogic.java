package edu.tju.ina.seeworld.logic;

import java.sql.Timestamp;
import java.util.List;

import net.sf.json.JSONArray;
import edu.tju.ina.seeworld.dao.user.IPrivacySettingDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.PrivacySetting;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.PrivacySettingVO;

public class PrivacySettingLogic implements IPrivacySettingLogic {
	private IPrivacySettingDAO privacySettingDao;
	private IUserDAO userDao;
	private JSONArray privacyList;

	public JSONArray getPrivacyList() {
		return privacyList;
	}

	public void setPrivacySettingDao(IPrivacySettingDAO privacySettingDao) {
		this.privacySettingDao = privacySettingDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public void setPrivacy(PrivacySettingVO privacySetting)
			throws SeeWorldException {
		Short typeId = privacySetting.getTypeId();
		String userId = privacySetting.getUserId();
		User user = userDao.findById(userId);
		Boolean isConcealed = true;
		Timestamp addTime = new Timestamp(System.currentTimeMillis());
		PrivacySetting instance = new PrivacySetting();
		List<PrivacySetting> list = privacySettingDao.findByTwoProperty(
				IPrivacySettingDAO.USER, user, IPrivacySettingDAO.TYPEID,
				typeId);
		boolean saveNotUpdate = true;
		if (list.size() > 0) {
			instance = list.get(0);
			saveNotUpdate = false;
		}
		instance.setUser(user);
		instance.setConcealed(isConcealed);
		instance.setAddTime(addTime);
		if (saveNotUpdate) {
			privacySettingDao.save(instance);
		} else {
			privacySettingDao.update(instance);
		}
	}

	public void showUserSetting(PrivacySettingVO privacySetting) throws SeeWorldException {
		String userId = privacySetting.getUserId();
		User user = userDao.findById(userId);
		List<PrivacySetting> list = privacySettingDao.findByProperty(
				IPrivacySettingDAO.USER, user);
		privacyList = new JSONArray();
		privacyList.addAll(list);
	}

	public void initUserSetting(User user) throws SeeWorldException {
		// 初始化用户隐私设置 默认均为可见
		PrivacySetting p = new PrivacySetting();
		p.setConcealed(false);
		p.setUser(user);
		p.setTypeId(Constant.USER_OPERATION_VIEW);
		privacySettingDao.save(p);

		PrivacySetting p1 = new PrivacySetting();
		p1.setConcealed(false);
		p1.setUser(user);
		p1.setTypeId(Constant.USER_OPERATION_COLLECT);
		privacySettingDao.save(p1);

		PrivacySetting p2 = new PrivacySetting();
		p2.setConcealed(false);
		p2.setUser(user);
		p2.setTypeId(Constant.USER_OPERATION_COMMENT);
		privacySettingDao.save(p2);
	}
}
