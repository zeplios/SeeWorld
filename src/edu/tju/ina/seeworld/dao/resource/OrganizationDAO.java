package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Organization;

/**
 * @see edu.tju.ina.seeworld.po.resource.Organization
 * @author Uranus
 */

public class OrganizationDAO extends BaseHibernateDAO<Organization> implements
		IOrganizationDAO {
	
	protected void initDao() {
		super.init(Organization.class.getName());
	}
	
	public List<Organization> findByName(Object name) throws SeeWorldException {
		return findByProperty(NAME, name);
	}

	public List<Organization> findByPage(int offset, int length) throws SeeWorldException{
		String hql="select d from Organization d ";
		return getListByPage(hql, offset, length);
	}
}