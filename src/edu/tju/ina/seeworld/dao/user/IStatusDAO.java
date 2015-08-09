package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Status;
import edu.tju.ina.seeworld.po.user.User;

public interface IStatusDAO extends IBaseHibernateDAO<Status> {
	public static final String USER = "user";

	/**
	 * 返回用户的所有状态
	 * @param user
	 * @return
	 */
	public List<Status> findByUser(User user) throws SeeWorldException;

}
