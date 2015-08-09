package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.KeyWord;
import edu.tju.ina.seeworld.po.resource.KeyWordRelationship;

/**
 * @see edu.tju.ina.seeworld.po.resource.KeyWordRelationship
 * @author Uranus
 * */

public class KeyWordRelationshipDAO extends
		BaseHibernateDAO<KeyWordRelationship> implements
		IKeyWordRelationshipDAO {
	protected void initDao() {
		super.init(KeyWordRelationship.class.getName());
	}

	public KeyWordRelationship findById(Integer id) throws SeeWorldException {
		return super.findById(id);
	}

	public List<KeyWordRelationship> findByWeight(Object weight)
			throws SeeWorldException {
		return findByProperty(WEIGHT, weight);
	}

	public List<KeyWordRelationship> findByList(final List<KeyWord> list) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session
						.createQuery("from KeyWordRelationship where keyword_id in (:list)");
				query.setParameterList("list", list);
				List result = query.list();
				return result;
			}
		});
	}

	public List<KeyWordRelationship> findByListWithinPage(
			final List<KeyWord> list, final Integer start,
			final Integer MaxResult) {
		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session
						.createQuery("from KeyWordRelationship where keyword_id in (:list)");
				query.setParameterList("list", list);
				query.setFirstResult(start);
				query.setMaxResults(MaxResult);
				List result = query.list();
				return result;
			}
		});
	}

}