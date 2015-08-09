package edu.tju.ina.seeworld.dao.resource;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Multimedia;

/**
 * @author Uranus
 */

public class MultimediaDAO extends BaseHibernateDAO<Multimedia> implements
		IMultimediaDAO {

	protected void initDao() {
		super.init(Multimedia.class.getName());
	}

	public List<Multimedia> findByName(Object name) throws SeeWorldException {
		return findByProperty(TITLE, name);
	}

	public List<Multimedia> findByImage(Object image) throws SeeWorldException {
		return findByProperty(IMAGE, image);
	}

	public List<Multimedia> findByPath(Object path) throws SeeWorldException {
		return findByProperty(PATH, path);
	}

	public List<Multimedia> findBySize(Object size) throws SeeWorldException {
		return findByProperty(SIZE, size);
	}

	public List<Multimedia> findByProcess(Object process)
			throws SeeWorldException {
		return findByProperty(PROCESS, process);
	}

	public List<Multimedia> findByDelete(Object delete)
			throws SeeWorldException {
		return findByProperty(DELETE, delete);
	}

	public List<Multimedia> findByResourceType(Object resourceType)
			throws SeeWorldException {
		return findByProperty(RESOURCE, resourceType);
	}

	public List<? extends Multimedia> findOrderedListByPropertyByPage(
			String propertyName, Object value, Object resourceName,
			boolean status, String orderColumn, int offset, int length)
			throws SeeWorldException {
		String hql = null;
		if (StringUtils.isNotBlank(propertyName)) {
			if (propertyName.equals(IMultimediaDAO.CATEGORY)) {
				hql = "select m from Multimedia m join m.category c where c.id=? and m.resource.name=? and m.status=? order by m."
						+ orderColumn;
			} else {
				hql = "select m from Multimedia m where m."
						+ propertyName
						+ "=? and m.resource.name=? and m.status=? order by m."
						+ orderColumn;
			}
			return getListByPage(hql, offset, length, value, resourceName,status);
		} else {
			hql = "select m from Multimedia m where m.resource.name=? and m.status=? order by m."
					+ orderColumn;
			return getListByPage(hql, offset, length, resourceName, status);
		}
	}

	@SuppressWarnings("unchecked")
	public List<? extends Multimedia> findByListAndCondition(
			final List<Integer> MultimeidaIdList, final String condition,
			final int start, final int maxsize) throws SeeWorldException {
		return (List<? extends Multimedia>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session
						.createQuery("from Multimedia where id in(:MultimeidaIdList)"
								+ condition);
				query.setParameterList("MultimeidaIdList", MultimeidaIdList);
				query.setFirstResult(start);
				query.setMaxResults(maxsize);
				List<? extends Multimedia> result = query.list();
				return result;
			}
		});
	}

	public List<String> getPublishedYearList(String resourceId)
			throws SeeWorldException {
		List<? extends Multimedia> list = findByQuery(
				"select m.publishedYear from Multimedia m where m.resource.id=? group by m.publishedYear",
				resourceId);
		List<String> res = new ArrayList<String>();
		for (Object o : list.toArray()) {
			res.add(o.toString());
		}
		return res;
	}

	public int getCountForSetProperty(String propertyName, String fieldName,
			Object value, Object resourcName, boolean status)
			throws SeeWorldException {
		final String hql = "select count(m) from Multimedia m join m."
				+ propertyName + " c where (c." + fieldName
				+ "=?) and m.resource.name=? and m.status=?";
		Object res = (Object) findByQuery(hql,
				new Object[] { value, resourcName, status }).get(0);
		return Integer.valueOf(res.toString());
	}

}