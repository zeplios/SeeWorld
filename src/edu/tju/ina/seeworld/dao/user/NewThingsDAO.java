package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.user.NewThings;
import edu.tju.ina.seeworld.po.user.User;

public class NewThingsDAO extends BaseHibernateDAO<NewThings> implements INewThingsDAO{

	protected void initDao() {
		super.init(NewThings.class.getName());
	}
	public List<NewThings> findByOwner(User owner) throws SeeWorldException{
		return findByProperty(USER,owner);
	}

	public List<NewThings> findByList(final List<String> userIdList,final Integer offset,final Integer length) throws SeeWorldException{
		return (List)getHibernateTemplate().execute(new HibernateCallback() {public Object doInHibernate(Session session)
		throws HibernateException {
			Query query = session.createQuery("from NewThings where sender_id in(:userid) order by id desc");
			query.setParameterList("userid", userIdList);
			query.setFirstResult(offset);
			query.setMaxResults(length);
			List result=query.list();
			return result;
		}
	});
	}
	
	public List<NewThings> findByTargetIdAndResourceType(Integer targetId,Resource reousrce) throws SeeWorldException{
		return findByTwoProperty(TARGETID, targetId, RESOURCE, reousrce);
	}
}
