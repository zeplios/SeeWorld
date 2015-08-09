package edu.tju.ina.seeworld.dao.rbac;

import edu.tju.ina.seeworld.dao.common.RbacDAO;
import edu.tju.ina.seeworld.po.rbac.Setting;

/**
 * A data access object (DAO) providing persistence and search support for
 * Setting entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.tju.ina.seeworld.po.rbac.Setting
 * @author MyEclipse Persistence Tools
 */

public class SettingDAO extends RbacDAO<Setting> {

	protected void initDao() {
		super.init(Setting.class.getName());
	}

}