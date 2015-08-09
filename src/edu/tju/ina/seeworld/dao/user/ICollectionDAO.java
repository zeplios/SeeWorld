package edu.tju.ina.seeworld.dao.user;


import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.user.Collection;

public interface ICollectionDAO extends IBaseHibernateDAO<Collection>{
	public static final String TARGETID="targetID";
	public static final String RESOURCE="resource";
	public static final String USER="user";
	public List<Collection> findByList(final List<Integer> CollectionIdList) throws SeeWorldException;
	public List<Collection> findByTargetIdAndResourceType(Integer targetId,Resource reousrce) throws SeeWorldException;

}
