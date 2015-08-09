package edu.tju.ina.seeworld.dao.rbac;

import edu.tju.ina.seeworld.dao.common.RbacDAO;
import edu.tju.ina.seeworld.po.rbac.Resource;

/**
 * A data access object (DAO) providing persistence and search support for
 * Resource entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see tju.ina.uranus.po.Resource
 * @author MyEclipse Persistence Tools
 */

public class ResourceDAO extends RbacDAO<Resource> {

	protected void initDao() {
		super.init(Resource.class.getName());
	}

}