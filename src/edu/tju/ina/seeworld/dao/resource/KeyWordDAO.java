package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.KeyWord;

/**
 * A data access object (DAO) providing persistence and search support for
 * KeyWord entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.tju.ina.seeworld.po.resource.KeyWord
 * @author MyEclipse Persistence Tools
 */

public class KeyWordDAO extends BaseHibernateDAO<KeyWord> implements
		IKeyWordDAO {

	protected void initDao() {
		super.init(KeyWord.class.getName());
	}
	public List<KeyWord> findByKeyword(Object keyword) throws SeeWorldException {
		return findByProperty(KEYWORD, keyword);
	}
	public List<KeyWord> findBySQL(final String queryString) {
		return (List)getHibernateTemplate().execute(new HibernateCallback() {public Object doInHibernate(Session session)
		throws HibernateException {
			Query query = session.createSQLQuery(queryString);
			List result=query.list();
			return result;}
	});
		}
}