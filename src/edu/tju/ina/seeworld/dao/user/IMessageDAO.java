package edu.tju.ina.seeworld.dao.user;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Message;

public interface IMessageDAO extends IBaseHibernateDAO<Message> {
	public static final String RECEIVER = "receiver";
	public static final String CONTENT = "content";
	public static final String READ = "read";
	public static final String SENDER = "sender";
	Integer TYPE_FOR_ALL=0;//显示出所有人的留言
	Integer TYPE_FOR_REPLY=1;//仅显示回复双方的留言
	public List<Message> findByPropertyLimited(final String propertyName,
			final Object value, final int start, final int maxsize) throws SeeWorldException;
	public void updateReaded(Integer id) throws SeeWorldException;
	public List<Message> findByContent(Object content) throws SeeWorldException;

	/**
	 * 根据是否已读的状态查找留言
	 * @param state
	 * @return
	 */
	public List<Message> findByRead(Object state) throws SeeWorldException;
}
