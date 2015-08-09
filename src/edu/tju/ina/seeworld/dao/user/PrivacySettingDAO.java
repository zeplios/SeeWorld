package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Operation;
import edu.tju.ina.seeworld.po.user.PrivacySetting;
import edu.tju.ina.seeworld.po.user.User;

public class PrivacySettingDAO extends BaseHibernateDAO<PrivacySetting>
		implements IPrivacySettingDAO {
	protected void initDao() {
		super.init(PrivacySetting.class.getName());
	}
	public List<PrivacySetting> findByUserAndOperation(User user,
			Operation operation) throws SeeWorldException{
		return findByTwoProperty(USER, user, TYPEID, operation);
	}

}
