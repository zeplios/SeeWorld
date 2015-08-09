package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.user.Comment;

public interface ICommentDAO extends IBaseHibernateDAO<Comment> {
	public static final String TITLE = "title";
	public static final String TARGETID = "targetID";
	public static final String RESOURCE = "resource";
	public static final String USER = "user";

	public List<Comment> findByPropertyLimited(final String propertyName,
			final Object value, final int start, final int maxsize)
			throws SeeWorldException;

	public List<Comment> findByTitle(Object title) throws SeeWorldException;

	public List<Comment> findByTargetIdAndResourceType(Integer targetId,
			Resource resource) throws SeeWorldException;

	public List<Comment> findByTwoPropertyLimited(final String propertyName,
			final Object value, String anotherPropertyName,
			Object anotherValue, final int start, final int maxsize)
			throws SeeWorldException;

	public List<Comment> findByList(final List<Integer> CommentIdList)
			throws SeeWorldException;
}
