package edu.tju.ina.seeworld.dao.user;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.NewThingsReply;

public class NewThingsReplyDAO extends BaseHibernateDAO<NewThingsReply>
		implements INewThingsReplyDAO {

	protected void initDao() {
		super.init(NewThingsReply.class.getName());
	}
	public void updateReaded(final Integer ID,final String receiver) throws SeeWorldException {
		try {
			getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException {
							int i=session.createSQLQuery("update newThingsReply set readed=1 where newThings="+ID+" and receiver='"+receiver+"' and readed=0").executeUpdate();
							return i;
							
						}
					});
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}
}
