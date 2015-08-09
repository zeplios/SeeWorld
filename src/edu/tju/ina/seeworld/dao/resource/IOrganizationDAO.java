package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Organization;

public interface IOrganizationDAO extends IBaseHibernateDAO<Organization> {
	public static final String NAME = "name";

	public List<Organization> findByName(Object name) throws SeeWorldException;
	public List<Organization> findByPage(int offset, int length)
	throws SeeWorldException;

}
