package edu.tju.ina.seeworld.dao.resource;

import java.util.ArrayList;
import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.AreaAndCountry;

/**
 * @see edu.tju.ina.seeworld.po.resource.AreaAndCountry
 * @author MyEclipse Persistence Tools
 */

public class AreaAndCountryDAO extends BaseHibernateDAO<AreaAndCountry>
		implements IAreaAndCountryDAO {

	protected void initDao() {
		super.init(AreaAndCountry.class.getName());
	}

	public List<AreaAndCountry> findByName(Object name)
			throws SeeWorldException {
		return findByProperty(NAME, name);
	}

	public List<AreaAndCountry> findByAlias(Object alias)
			throws SeeWorldException {
		return findByProperty(ALIAS, alias);
	}

	public List<AreaAndCountry> findByCount(Object count)
			throws SeeWorldException {
		return findByProperty(COUNT, count);
	}

	public List<AreaAndCountry> findCertainResourceAreaAndCountryList(
			Resource resource) throws SeeWorldException {
		String hql = null;
		List<AreaAndCountry> list = new ArrayList<AreaAndCountry>(0);
		hql = "select a FROM AreaAndCountry a join a.multimedias m where m.resource.id=? group by a.name";
		list = findByQuery(hql, resource.getId());
		return list;
	}
	
	public List<AreaAndCountry> findByPage(int offset, int length) throws SeeWorldException{
		String hql="select a from AreaAndCountry a ";
		return getListByPage(hql, offset, length);
	}
	
}