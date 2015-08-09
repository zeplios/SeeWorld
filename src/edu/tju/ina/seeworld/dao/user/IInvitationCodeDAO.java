package edu.tju.ina.seeworld.dao.user;

import java.sql.Timestamp;
import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.InvitationCode;
import edu.tju.ina.seeworld.po.user.User;

public interface IInvitationCodeDAO extends IBaseHibernateDAO<InvitationCode>{
	public final static String USER = "user";
	public final static String EXPIRED_TIME = "expiredTime";
	public final static String USED = "used";
	public final static String IID="iid";
	
	public List<InvitationCode> findByUser(User user) throws SeeWorldException;
	public List<InvitationCode> findByUsed(Boolean used) throws SeeWorldException;
	public List<InvitationCode> findByExpiredTime(Timestamp ts) throws SeeWorldException;
}
