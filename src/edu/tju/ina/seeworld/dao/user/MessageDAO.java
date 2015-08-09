package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Message;

/**
 * @see edu.tju.ina.seeworld.po.user.Message
 * @author Uranus
 */

public class MessageDAO extends BaseHibernateDAO<Message> implements
		IMessageDAO {

	protected void initDao() {
		super.init(Message.class.getName());
	}

	@SuppressWarnings("unchecked")
	public List<Message> findByPropertyLimited(final String propertyName,
			final Object value, final int start, final int maxsize) throws SeeWorldException{
		try {
			return (List) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							Query query1 = session
									.createQuery("from Message as model where model."
											+ propertyName + "= ?");
							query1.setParameter(0, value);
							query1.setFirstResult(start);
							query1.setMaxResults(maxsize);
							List result = query1.list();
							return result;
						}
					});
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public List<Message> findByContent(Object content) throws SeeWorldException{
		return findByProperty(CONTENT, content);
	}

	/* (non-Javadoc)
	 * @see edu.tju.ina.seeworld.dao.user.IMessageDAO#findByRead(java.lang.Object)
	 */
	public List<Message> findByRead(Object state) throws SeeWorldException{
		return findByProperty(READ, state);
	}
	public void updateReaded(Integer id) throws SeeWorldException {
			Message message = findById(id);
			message.setRead(true);
			update(message);
	}
}