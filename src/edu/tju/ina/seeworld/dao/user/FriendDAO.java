package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Friend;
import edu.tju.ina.seeworld.po.user.User;

/**
 * @see edu.tju.ina.seeworld.struts.vo.User_VO.tju.ina.seeworld.po.user.Friend
 * @author Uranus
 */

public class FriendDAO extends BaseHibernateDAO<Friend> implements IFriendDAO {
	protected void initDao() {
		super.init(Friend.class.getName());
	}
	
	public List<Friend> findByOfferAndTheOther(User offer, User theOther) throws SeeWorldException{
		return findByTwoProperty(OFFER, offer, THEOTHER, theOther);
	}

	public List<Friend> findByTheOtherAndOffer(User theOther, User offer) throws SeeWorldException{
		return findByTwoProperty(THEOTHER, theOther, OFFER, offer);
	}
	public List<Friend> findBySQL(final String sql){
		return (List)getHibernateTemplate().execute(new HibernateCallback() {public Object doInHibernate(Session session)
		throws HibernateException {
			Query query = session.createSQLQuery(sql);
			List result=query.list();
			return result;
		}
	});
	}

}