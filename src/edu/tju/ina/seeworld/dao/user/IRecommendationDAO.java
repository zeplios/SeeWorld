package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.user.Recommendation;
import edu.tju.ina.seeworld.po.user.User;

public interface IRecommendationDAO extends IBaseHibernateDAO<Recommendation> {
	public static final String RECEIVER = "receiver";
	public static final String SENDER = "sender";
	public static final String TARGETID = "targetID";
	public static final String READ = "isRead";
	public static final String RESOURCE = "resource";

	public List<Recommendation> findByReceiver(User receiver)
			throws SeeWorldException;

	public List<Recommendation> findBySender(User sender)
			throws SeeWorldException;

	public List<Recommendation> findByTargetIdAndResourceType(Integer targetId,
			Resource resource) throws SeeWorldException;

	public List<Recommendation> findByRead(Boolean read)
			throws SeeWorldException;
}
