package edu.tju.ina.seeworld.dao.user;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.NewThingsReply;

public interface INewThingsReplyDAO extends IBaseHibernateDAO<NewThingsReply> {
	public void updateReaded(final Integer ID,final String receiver) throws SeeWorldException;
}
