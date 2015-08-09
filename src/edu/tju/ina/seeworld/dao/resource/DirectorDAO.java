package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Director;

/**
 * A data access object (DAO) providing persistence and search support for
 * Director entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.tju.ina.seeworld.po.resource.Director
 * @author MyEclipse Persistence Tools
 */

public class DirectorDAO extends BaseHibernateDAO<Director> implements
		IDirectorDAO {

	protected void initDao() {
		super.init(Director.class.getName());
	}

	public List<Director> findByAlias(Object alias) throws SeeWorldException {
		return findByProperty(ALIAS, alias);
	}

	public List<Director> findByName(Object name) throws SeeWorldException {
		return findByProperty(NAME, name);
	}

	public List<Director> findByCount(Object count) throws SeeWorldException {
		return findByProperty(COUNT, count);
	}

	public List<Director> findByPage(int offset, int length) throws SeeWorldException{
		String hql="select d from Director d ";
		return getListByPage(hql, offset, length);
	}
}