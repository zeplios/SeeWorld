package edu.tju.ina.seeworld.dao.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import edu.tju.ina.seeworld.exception.SeeWorldException;

public interface IBaseHibernateDAO<T> {
	public static final String ID = "id";
	public Serializable save(T transientInstance) throws SeeWorldException;

	public void update(T transientInstance) throws SeeWorldException;

	public void delete(T persistentInstance) throws SeeWorldException;
	/**
	 * 批量删除
	 * @param entities
	 */
	public void deleteAll(Collection<? extends T> entities) throws SeeWorldException;
	public T merge(T detachedInstance) throws SeeWorldException;

	public void attachClean(T instance) throws SeeWorldException;

	public void attachDirty(T instance) throws SeeWorldException;

	public T findById(Serializable id) throws SeeWorldException;

	public List<T> findAll(String orderBy, boolean requireOrdered) throws SeeWorldException;

	public List<T> findByProperty(String propertyName, Object value) throws SeeWorldException;

	public List<T> findByTwoProperty(String propertyName1, Object value1,
			String propertyName2, Object value2) throws SeeWorldException;

	public List<T> findByThreeProperty(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3) throws SeeWorldException;

	public List<T> findByQuery(String queryString) throws SeeWorldException;

	public List<T> findByQuery(String queryString, Object[] args) throws SeeWorldException;

	public List<T> findByQuery(String queryString, Object arg) throws SeeWorldException;
	
	public int getCount(Map<String,Object> params) throws SeeWorldException;
	/**
	 * 分页查询方法，返回从offset起的length条内容
	 * @param hql HQL查询语言
	 * @param offset 起始位置
	 * @param length 每页长度
	 * @return
	 */
	public List<T> getListByPage(final String hql, final int offset,
		final int length,final Object... params) throws SeeWorldException;
	
	/**
	 * 对于主键名称为id，且生成模式为人工指定，且为整数的，生成当前可用的最小Id
	 * @param tablename 数据表表明，如user， 此处使用sql语句，因此不再使用hql映射的表名
	 * @param start 最小的id值，如0， 1， 10000
	 * @return
	 * @throws SeeWorldException
	 */
	public String getNextAvaliableId(final String tablename, final int start) throws SeeWorldException;
}
