package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Friend;
import edu.tju.ina.seeworld.po.user.User;

public interface IFriendDAO extends IBaseHibernateDAO<Friend> {
	public static final String OFFER = "userOffer";
	public static final String THEOTHER = "userTheOther";

	public List<Friend> findByOfferAndTheOther(User offer, User theOther) throws SeeWorldException;

	public List<Friend> findByTheOtherAndOffer(User theOther, User offer) throws SeeWorldException;
	
	public List<Friend> findBySQL(final String sql) throws SeeWorldException;
}