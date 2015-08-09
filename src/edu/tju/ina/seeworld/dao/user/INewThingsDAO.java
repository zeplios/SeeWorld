package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.user.NewThings;
import edu.tju.ina.seeworld.po.user.User;

public interface INewThingsDAO extends IBaseHibernateDAO<NewThings> {
	public static final String USER="user";
	public static final String TARGETID ="targetId";
	public static final String RESOURCE="resource";
	
	/**
	 * 查找某一用户最近所进行的新鲜事
	 * @param owner
	 * @return
	 */
	public List<NewThings> findByOwner(User owner) throws SeeWorldException;
	public List<NewThings> findByList(final List<String> userIdList,final Integer offset,final Integer length) throws SeeWorldException;
	public List<NewThings> findByTargetIdAndResourceType(Integer targetId,Resource reousrce) throws SeeWorldException;
}
