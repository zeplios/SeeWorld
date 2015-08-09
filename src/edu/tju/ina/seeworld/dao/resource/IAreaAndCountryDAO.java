package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;

public interface IAreaAndCountryDAO extends IBaseHibernateDAO<AreaAndCountry> {
	public static final String NAME = "name";
	public static final String COUNT = "count";
	public static final String ALIAS = "alias";

	public List<AreaAndCountry> findByName(Object name)
			throws SeeWorldException;

	public List<AreaAndCountry> findByAlias(Object alias)
			throws SeeWorldException;
	
	public List<AreaAndCountry>  findCertainResourceAreaAndCountryList(Resource resource) throws SeeWorldException;
	
	public List<AreaAndCountry> findByPage(int offset, int length)
	throws SeeWorldException;
}
