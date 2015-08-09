package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Operation;
import edu.tju.ina.seeworld.po.user.PrivacySetting;
import edu.tju.ina.seeworld.po.user.User;

public interface IPrivacySettingDAO extends IBaseHibernateDAO<PrivacySetting> {
	public static final String USER = "user";
	public static final String TYPEID="typeID";
	public List<PrivacySetting> findByUserAndOperation(User user,Operation operation) throws SeeWorldException;
}
