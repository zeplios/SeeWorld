package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Director;

public interface IDirectorDAO extends IBaseHibernateDAO<Director> {
	public static final String NAME = "name";
	public static final String COUNT = "count";
	public static final String ALIAS = "alias";

	public List<Director> findByAlias(Object alias) throws SeeWorldException;

	public List<Director> findByName(Object name) throws SeeWorldException;

	public List<Director> findByCount(Object count) throws SeeWorldException;

	public List<Director> findByPage(int offset, int length)
			throws SeeWorldException;
}
