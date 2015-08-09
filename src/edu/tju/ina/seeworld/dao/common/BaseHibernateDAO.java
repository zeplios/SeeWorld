package edu.tju.ina.seeworld.dao.common;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.exception.SeeWorldRuntimeException;

public class BaseHibernateDAO<T> extends HibernateDaoSupport implements
		IBaseHibernateDAO<T> {
	/**
	 * 实例名
	 */
	protected String entityName;
	/**
	 * 具体操作的实体类名
	 */
	protected String className;

	public void init(String _className) {
		this.className = _className;
		entityName = className.substring(_className.lastIndexOf(".") + 1);
	}

	public Serializable save(T transientInstance) throws SeeWorldException {
		try {
			return getHibernateTemplate().save(transientInstance);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public void update(T transientInstance) throws SeeWorldException {
		try {
			/**
			 * 这里是原来的代码和说明：
			 * ---------------------------------------------------------
			 * // 此处作一修改，如果数据库表中有一对多或多对多记录，当执行update
			 * // 操作时，会报错  object with the same identifier value was already associated with the session
			 * // 执行下面一句，将当前session clear 可以解决，貌似是hibernate的一个bug
			 * getHibernateTemplate().getSessionFactory().getCurrentSession().clear();
			 * getHibernateTemplate().update(transientInstance);
			 * ---------------------------------------------------------
			 * 这是因为当前session中出现了两个相同的持久对象，原来的方法是先清除session，但是这样一来，延迟加载的时候就会报错说session已关闭，
			 * 此时如果关闭延迟加载，性能上就会受到影响，所以在这里结合try、catch改为merge实现，当然merge并不单纯是更新
			 * @author zhfch
			 */
			getHibernateTemplate().update(transientInstance);
		} catch (NonUniqueObjectException e) {
			getHibernateTemplate().merge(transientInstance);
		}
	}

	public void delete(T persistentInstance) throws SeeWorldException {
		try {
			getHibernateTemplate().delete(persistentInstance);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByExample(T instance) throws SeeWorldException {
		try {
			List<T> results = getHibernateTemplate().findByExample(instance);
			return results;
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	/**
	 * 将传入的detached状态的对象的属性复制到持久化对象中，并返回该持久化对象 。 如果该session中没有关联的持久化对象，加载一个;
	 * 如果传入对象未保存，保存一个副本并作为持久对象返回，传入对象依然保持detached状态。
	 * 
	 * @throws SeeWorldException
	 * 
	 * @see edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO#merge(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public T merge(T detachedInstance) throws SeeWorldException {
		try {
			T result = (T) getHibernateTemplate().merge(detachedInstance);
			return result;
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	/**
	 * 将传入的对象持久化并保存。 如果对象未保存（Transient状态），调用save方法保存。
	 * 如果对象已保存（Detached状态），调用update方法将对象与Session重新关联。
	 * 
	 * @throws SeeWorldException
	 * 
	 * @see edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO#attachDirty(java.lang.Object)
	 */
	public void attachDirty(T instance) throws SeeWorldException {
		try {
			getHibernateTemplate().saveOrUpdate(instance);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	/**
	 * 将传入的对象状态设置为Transient状态
	 * 
	 * @throws SeeWorldException
	 * 
	 * @see edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO#attachClean(java.lang.Object)
	 */
	public void attachClean(T instance) throws SeeWorldException {
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	/**
	 * 当查询字符串以%开头且以之结尾时使用模糊查询
	 * @author zhfch
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByProperty(String propertyName, Object value)
			throws SeeWorldException {
		try {
			String queryString;
			if(value.toString().startsWith("%") || value.toString().endsWith("%")){
				queryString = "from " + entityName
					+ " as model where model." + propertyName
					+ " like ? order by model.addTime";
			}
			else{
				queryString = "from " + entityName
					+ " as model where model." + propertyName
					+ "= ? order by model.addTime";
			}
			return getHibernateTemplate().find(queryString, value);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll(String orderBy, boolean requireOrdered)
			throws SeeWorldException {
		if (requireOrdered) {
			if (StringUtils.isBlank(orderBy)) {
				orderBy = " order by addTime";
			}
		}else{
			orderBy = "";
		}
		try {
			String queryString = "from " + entityName + orderBy;
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	@SuppressWarnings("unchecked")
	public T findById(Serializable id) throws SeeWorldException {
		try {
			T instance = (T) getHibernateTemplate().get(className, id);
			return instance;
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByTwoProperty(String propertyName1, Object value1,
			String propertyName2, Object value2) throws SeeWorldException {
		try {
			String queryString = "from " + entityName
					+ " as model where model." + propertyName1
					+ "= ? and model." + propertyName2
					+ "=? order by model.addTime";
			Object[] values = { value1, value2 };
			return getHibernateTemplate().find(queryString, values);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByThreeProperty(String propertyName1, Object value1,
			String propertyName2, Object value2, String propertyName3,
			Object value3) throws SeeWorldException {
		try {
			String queryString = "from " + entityName
					+ " as model where model." + propertyName1
					+ "= ? and model." + propertyName2 + "=? and model."
					+ propertyName3 + "=? order by model.addTime";
			Object[] values = { value1, value2, value3 };
			return getHibernateTemplate().find(queryString, values);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public void deleteAll(Collection<? extends T> entities)
			throws SeeWorldException {
		try {
			getHibernateTemplate().deleteAll(entities);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByQuery(String queryString) throws SeeWorldException {
		try {
			return getHibernateTemplate().find(queryString);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByQuery(String queryString, Object[] args)
			throws SeeWorldException {
		try {
			return getHibernateTemplate().find(queryString, args);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByQuery(String queryString, Object arg)
			throws SeeWorldException {
		try {
			return getHibernateTemplate().find(queryString, arg);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> getListByPage(final String hql, final int offset,
			final int length, final Object... params) throws SeeWorldException {
		List<T> list = new ArrayList<T>();
		try {
			HibernateTemplate tmp = getHibernateTemplate();
			list = tmp.executeFind(new HibernateCallback() {
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					Query query = session.createQuery(hql);
					if (params != null && params.length != 0) {
						for (int i = 0; i < params.length; i++) {
							if (params[i] instanceof String) {
								query.setString(i, (String) params[i]);
								continue;
							} else if (params[i] instanceof Short) {
								query.setShort(i, (Short) params[i]);
								continue;
							} else if (params[i] instanceof BigInteger) {
								query.setBigInteger(i, (BigInteger) params[i]);
								continue;
							} else if (params[i] instanceof Integer) {
								query.setInteger(i, (Integer) params[i]);
								continue;
							} else if (params[i] instanceof Timestamp) {
								query.setTimestamp(i, (Timestamp) params[i]);
								continue;
							} else if (params[i] instanceof Boolean) {
								query.setBoolean(i, (Boolean) params[i]);
								continue;
							} else {
								throw new SeeWorldRuntimeException(
										"Invalid pramameter type in pagination query.");
							}
						}
					}
					query.setFirstResult(offset);
					query.setMaxResults(length);
					List<T> list = query.list();
					return list;
				}
			});
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
		return list;
	}

	public int getCount(final Map<String, Object> params)
			throws SeeWorldException {
		StringBuilder hb = new StringBuilder("select count(model) from "
				+ entityName + " as model");
		if (params != null && params.size() > 0) {
			final String[] keys = params.keySet().toArray(new String[0]);
			hb.append(" where ");
			for (int i = 0; i < keys.length; i++) {
				String val = params.get(keys[i]).toString();
				String condition = "";
				if (StringUtils.contains(val, '=')
						&& StringUtils.containsIgnoreCase(val, "and")) {
					throw new SeeWorldRuntimeException(
							"Rejected sql condition values in case of sql injection potential");
				}
				if (NumberUtils.isNumber(val) || val.equalsIgnoreCase("true")
						|| val.equalsIgnoreCase("false")) {
					condition = "model." + keys[i] + "=" + val;
				} else {
					if(val.startsWith("%") && val.endsWith("%")){
						String field = "replace(model." + keys[i] + ", '·', '')";
						condition = field + " like '" + val + "'";
					}
					else
						condition = "model." + keys[i] + "='" + val + "'";
				}
				if (i == 0) {
					hb.append(condition);
				} else {
					hb.append(" AND " + condition);
				}
			}
		}
		final String hql = hb.toString();
		Long count = (Long) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						List<?> res = query.list();
						return res.get(0);
					}
				});
		return count.intValue();
	}
	
	public String getNextAvaliableId(final String tablename, final int start) throws SeeWorldException {
		// TODO Auto-generated method stub
		try {
			return (String) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						@SuppressWarnings("deprecation")
						Connection conn = session.connection();
						String sql = "SELECT (CASE WHEN EXISTS(SELECT * FROM " + tablename + " WHERE id=" + start + ")" +  
											"THEN MIN(id)+1 ELSE " + start + " END) FROM " + tablename + " WHERE id NOT IN" +  
											"(SELECT id-1 FROM " + tablename + ") AND id>=" + start;
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery(sql);
						if(rs.next())
							return rs.getString(1);
						else
							return null;
					}
				});
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}
}
