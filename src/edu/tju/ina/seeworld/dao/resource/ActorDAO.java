package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Actor;

/**
 * @see edu.tju.ina.seeworld.po.resource.Actor
 * @author Uranus
 */

public class ActorDAO extends BaseHibernateDAO<Actor> implements IActorDAO {

	public void initDao() {
		super.init(Actor.class.getName());
	}
	public List<Actor> findByName(Object name) throws SeeWorldException{
		return findByProperty(NAME, name);
	}

	public List<Actor> findByCount(Object count) throws SeeWorldException{
		return findByProperty(COUNT, count);
	}

	public List<Actor> findByAlias(Object count) throws SeeWorldException{
		return findByProperty(ALIAS, count);
	}

	public List<Actor> findByAreaAndCountry(Object count) throws SeeWorldException{
		return findByProperty(AREAANDCOUNTRY, count);
	}
	
	public List<Actor> findByPage(int offset, int length) throws SeeWorldException{
		String hql="select d from Actor d ";
		return getListByPage(hql, offset, length);
	}
	
}