package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Request;

public interface IRequestDAO extends IBaseHibernateDAO<Request>{
	public static final String SENDER = "sender";
	public static final String RECEIVER="reciever";
	public List<Request> findByTwoProperty(String propertyName1,Object value1,String propertyName2,Object value2) throws SeeWorldException;
}
