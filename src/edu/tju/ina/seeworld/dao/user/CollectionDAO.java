package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.user.Collection;

/**
 * A data access object (DAO) providing persistence and search support for
 * Collection entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.tju.ina.seeworld.struts.vo.User_VO.tju.ina.seeworld.po.user.Collection
 * @author MyEclipse Persistence Tools
 */

public class CollectionDAO extends BaseHibernateDAO<Collection> implements
		ICollectionDAO {

	protected void initDao() {
		super.init(Collection.class.getName());
	}

	public List<Collection> findByTargetIdAndResourceType(Integer targetId,
			Resource reousrce) throws SeeWorldException {
		return findByTwoProperty(TARGETID, targetId, RESOURCE, reousrce);
	}

	@SuppressWarnings("unchecked")
	public List<Collection> findByList(final List<Integer> CollectionIdList)  throws SeeWorldException{
		return (List<Collection>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session
						.createQuery("from Collection where ID in(:collectionid)");
				query.setParameterList("collectionid", CollectionIdList);
				List<Collection> result = query.list();
				return result;
			}
		});
	}
}