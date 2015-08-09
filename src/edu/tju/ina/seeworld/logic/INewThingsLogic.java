package edu.tju.ina.seeworld.logic;

import java.util.List;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.user.Collection;
import edu.tju.ina.seeworld.po.user.Comment;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.vo.MessageVO;
import edu.tju.ina.seeworld.vo.NewThingsVO;

public interface INewThingsLogic {

	public List<NewThingsVO> moreNewThings(int offset, int pagesize, User user)  throws SeeWorldException ;

	/**
	 * targetId为collection的id，resourceId和collection的resourceId保持一致
	 * @param user
	 * @param collection
	 * @throws SeeWorldException
	 */
	public void addCollectNewThings(User user, Collection collection) throws SeeWorldException;

	/**
	 * targetId为comment的id，resourceId和comment的resourceId保持一致
	 * @param user
	 * @param comment
	 * @throws SeeWorldException
	 */
	public void addCommentNewThings(User user, Comment comment) throws SeeWorldException;

	public void addViewNewThings(User user, Integer targetId,Resource resource) throws SeeWorldException;

	public void deleteNewThings(int ID) throws SeeWorldException;

	public Integer addNewThingsReply(Integer newThingsID, Boolean reply,
			User me, String toUserID, String content) throws SeeWorldException;

	public Integer deleteNewThingsReply(Integer ID) throws SeeWorldException;

	public List<MessageVO>  viewAllNewThingsReply(Integer ID, User me) throws SeeWorldException;

	public List<MessageVO>  viewNewReply(User me) throws SeeWorldException;

	public void setReplyReaded(Integer ID, User me) throws SeeWorldException;

	public NewThingsVO getOneNewThing(int ID, User user) throws SeeWorldException;
	
	public List<NewThingsVO> viewNewThingsOfUser(int start, int resultSize,
			User user, int typeId) throws SeeWorldException;
}
