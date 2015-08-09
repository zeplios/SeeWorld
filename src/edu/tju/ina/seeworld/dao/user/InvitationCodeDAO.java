package edu.tju.ina.seeworld.dao.user;

import java.sql.Timestamp;
import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.InvitationCode;
import edu.tju.ina.seeworld.po.user.User;

public class InvitationCodeDAO extends BaseHibernateDAO<InvitationCode>
		implements IInvitationCodeDAO {
	protected void initDao() {
		super.init(InvitationCode.class.getName());
	}

	public List<InvitationCode> findByExpiredTime(Timestamp ts) throws SeeWorldException {
		return findByProperty(EXPIRED_TIME, ts);
	}

	public List<InvitationCode> findByUsed(Boolean used) throws SeeWorldException {
		return findByProperty(USED, used);
	}

	public List<InvitationCode> findByUser(User user) throws SeeWorldException {
		return findByProperty(USER, user);
	}
}
