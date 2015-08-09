package edu.tju.ina.seeworld.dao.common;

import edu.tju.ina.seeworld.exception.SeeWorldException;



/**
 * 用户角色与权限管理DAO的公共接口
 * @author uranus
 *
 * @param <T>
 */
public interface IRbacDAO<T> extends IBaseHibernateDAO<T> {
	
	public static final String NAME = "name";

	public T findByName(Object name) throws SeeWorldException;
}
