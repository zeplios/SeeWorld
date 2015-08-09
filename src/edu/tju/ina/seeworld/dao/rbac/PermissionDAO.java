package edu.tju.ina.seeworld.dao.rbac;

import edu.tju.ina.seeworld.dao.common.RbacDAO;
import edu.tju.ina.seeworld.po.rbac.Permission;

/**
 * A data access object (DAO) providing persistence and search support for
 * Permission entities. Transaction control of the save(), update() and delete()
 * Permissions can directly support Spring container-managed transactions or
 * they can be augmented to handle user-managed Spring transactions. Each of
 * these methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see tju.ina.uranus.po.Permission
 * @author MyEclipse Persistence Tools
 */

public class PermissionDAO extends RbacDAO<Permission> {

	protected void initDao() {
		super.init(Permission.class.getName());
	}
	
}