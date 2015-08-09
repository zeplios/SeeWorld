package edu.tju.ina.seeworld.dao.rbac;

import edu.tju.ina.seeworld.dao.common.RbacDAO;
import edu.tju.ina.seeworld.po.rbac.Role;

/**
 * 
 * @see tju.ina.uranus.po.Role
 * @author uranus
*/
public class RoleDAO extends RbacDAO<Role> {

	protected void initDao() {
		super.init(Role.class.getName());
	}

}