package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.user.Recommendation;
import edu.tju.ina.seeworld.po.user.User;

public class RecommendationDAO extends BaseHibernateDAO<Recommendation>
		implements IRecommendationDAO {

	protected void initDao() {
		super.init(Recommendation.class.getName());
	}

	public List<Recommendation> findByTargetIdAndResourceType(Integer targetId,
			Resource resource) throws SeeWorldException {
		return findByTwoProperty(TARGETID, targetId, RESOURCE, resource);
	}

	public List<Recommendation> findByReceiver(User receiver)
			throws SeeWorldException {
		return findByProperty(RECEIVER, receiver);
	}

	public List<Recommendation> findBySender(User sender)
			throws SeeWorldException {
		return findByProperty(SENDER, sender);
	}

	public List<Recommendation> findByRead(Boolean read)
			throws SeeWorldException {
		return findByProperty(READ, read);
	}
}
