package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.user.Comment;

/**
 * A data access object (DAO) providing persistence and search support for
 * Comment entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see Comment
 * @author Uranus
 */

public class CommentDAO extends BaseHibernateDAO<Comment> implements
		ICommentDAO {

	protected void initDao() {
		super.init(Comment.class.getName());
	}

	public List<Comment> findByPropertyLimited(final String propertyName,
			final Object value, final int start, final int maxsize)
			throws SeeWorldException {
		try {
			String query1 = "from Comment as model where model." + propertyName
					+ "='" + value + "' order by addTime desc";
			return getListByPage(query1, start, maxsize);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public List<Comment> findByTwoPropertyLimited(final String propertyName,
			final Object value, String anotherPropertyName,
			Object anotherValue, final int start, final int maxsize)
			throws SeeWorldException {
		try {
			String query1 = "from Comment as model where model." + propertyName
					+ "='" + value + "' and " + anotherPropertyName + "='"
					+ anotherValue + "' order by addTime desc";
			return getListByPage(query1, start, maxsize);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public List<Comment> findByTitle(Object title) throws SeeWorldException {
		return findByProperty(TITLE, title);
	}

	@SuppressWarnings("unchecked")
	public List<Comment> findByList(final List<Integer> CommentIdList)
			throws SeeWorldException {
		return (List<Comment>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException {
				Query query = session
						.createQuery("from Comment where id in(:CommentIdList)");
				query.setParameterList("CommentIdList", CommentIdList);
				List<Comment> result = query.list();
				return result;
			}
		});
	}

	public List<Comment> findByTargetIdAndResourceType(Integer targetId,
			Resource reousrce) throws SeeWorldException {
		return findByTwoProperty(TARGETID, targetId, RESOURCE, reousrce);
	}
}