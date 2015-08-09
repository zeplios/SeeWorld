package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Actor;

public interface IActorDAO extends IBaseHibernateDAO<Actor> {
	public static final String NAME = "name";
	public static final String COUNT = "count";
	public static final String ALIAS = "alias";
	public static final String AREAANDCOUNTRY = "areaAndCountry";

	public List<Actor> findByName(Object name)  throws SeeWorldException;

	public List<Actor> findByCount(Object count)  throws SeeWorldException;

	public List<Actor> findByAlias(Object count)  throws SeeWorldException;
	
	public List<Actor> findByAreaAndCountry(Object count)  throws SeeWorldException;
	public List<Actor> findByPage(int offset, int length)
	throws SeeWorldException;

}
