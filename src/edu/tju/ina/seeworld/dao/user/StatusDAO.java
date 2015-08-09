package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Status;
import edu.tju.ina.seeworld.po.user.User;

public class StatusDAO extends BaseHibernateDAO<Status> implements IStatusDAO{

	protected void initDao(){
		super.init(Status.class.getName());
	}
	
	public List<Status> findByUser(User user) throws SeeWorldException{
		return findByProperty(USER,user);
	}
}
