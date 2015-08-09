package edu.tju.ina.seeworld.logic;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import org.springframework.util.Assert;

import edu.tju.ina.seeworld.dao.resource.IMovieDAO;
import edu.tju.ina.seeworld.dao.resource.IMultimediaDAO;
import edu.tju.ina.seeworld.dao.resource.ISingleSerialDAO;
import edu.tju.ina.seeworld.dao.resource.IVideoDAO;
import edu.tju.ina.seeworld.dao.user.ICommentDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.Movie;
import edu.tju.ina.seeworld.po.user.Comment;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.util.DateUtil;
import edu.tju.ina.seeworld.util.IDAssistant;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.util.enums.MultimediaType;
import edu.tju.ina.seeworld.vo.CommentVO;

/**
 *Title: CommentLogic Created on 2010-3-26 Description: The logical class of
 * Comment
 * 
 * @author wzh
 *@version 1.1
 */

public class CommentLogic implements ICommentLogic {
	private ICommentDAO commentDao;
	private IMultimediaDAO multimediaDao;
	private IUserDAO userDao;
	private INewThingsLogic newThingsLogic;
	private IMovieDAO movieDao;
	private ISingleSerialDAO singleSerialDao;
	private IVideoDAO videoDao;
	private IDAssistant iDAssistant;
	private VOPOTransformator vOPOTransformator;

	public Integer getTargetId(Integer id, String resourceId)
			throws SeeWorldException {
		String resourceName = iDAssistant.getResourceName(resourceId);
		if (resourceName.equals(MultimediaType.Movie.name())) {
			Movie movie = movieDao.findById(id);
		}
		return id;
	}

	public CommentVO addComment(User user, Integer targetId, String resourceId,
			String title, String content, Integer replyTo)
			throws SeeWorldException {
		Comment model = new Comment();
		Comment reply = null;
		if (replyTo != 0) {
			reply = commentDao.findById(replyTo);
		}
		Assert.notNull(user);
		Assert.notNull(user.getId());
		model.setUser(user);
		model.setContent(content);
		model.setTitle(title);
		model.setTargetID(getTargetId(targetId, resourceId));
		Resource resource = new Resource();
		resource.setId(resourceId);
		model.setResource(resource);
		Timestamp addtime = DateUtil.getCurrentTimestamp();
		model.setAddTime(addtime);
		model.setReplyTo(reply);
		model.setSupport(0);
		model.setObject(0);
		Integer id = (Integer)commentDao.save(model);
		newThingsLogic.addCommentNewThings(user, model);
		model.setId(id);
		return new CommentVO(model);
	}

	public int support(Integer CommentID) throws SeeWorldException {
		Comment comment = commentDao.findById(CommentID);
		Integer support = comment.getSupport();
		support++;
		comment.setSupport(support);
		commentDao.update(comment);
		return support;
	}

	public Comment findById(Serializable id) throws SeeWorldException{
		return commentDao.findById(id);
	}

	public void updateComment(Comment c) throws SeeWorldException{
		commentDao.update(c);
	}

	public void deleteComment(Comment c) throws SeeWorldException{
		commentDao.delete(c);
	}

	
	public int object(Integer CommentID) throws SeeWorldException {
		Comment comment = commentDao.findById(CommentID);
		Integer object = comment.getObject();
		object++;
		comment.setObject(object);
		commentDao.update(comment);
		return object;
	}

	public void setCommentDao(ICommentDAO commentDao) {
		this.commentDao = commentDao;
	}

	public void setMultimediaDao(IMultimediaDAO multimediaDao) {
		this.multimediaDao = multimediaDao;
	}

	public void setNewThingsLogic(INewThingsLogic newThingsLogic) {
		this.newThingsLogic = newThingsLogic;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public IMovieDAO getMovieDao() {
		return movieDao;
	}

	public void setMovieDao(IMovieDAO movieDao) {
		this.movieDao = movieDao;
	}

	public ISingleSerialDAO getSingleSerialDao() {
		return singleSerialDao;
	}

	public void setSingleSerialDao(ISingleSerialDAO singleSerialDao) {
		this.singleSerialDao = singleSerialDao;
	}

	public IVideoDAO getVideoDao() {
		return videoDao;
	}

	public void setVideoDao(IVideoDAO videoDao) {
		this.videoDao = videoDao;
	}

	public ICommentDAO getCommentDao() {
		return commentDao;
	}

	public IMultimediaDAO getMultimediaDao() {
		return multimediaDao;
	}

	public IUserDAO getUserDao() {
		return userDao;
	}

	public INewThingsLogic getNewThingsLogic() {
		return newThingsLogic;
	}

	public void setiDAssistant(IDAssistant iDAssisstant) {
		this.iDAssistant = iDAssisstant;
	}

	public VOPOTransformator getvOPOTransformator() {
		return vOPOTransformator;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public List<CommentVO> findCommentsByPage(Integer targetId,
			String resourceId, Integer offset, Integer pageSize)
			throws SeeWorldException {
		targetId = getTargetId(targetId, resourceId);
		List<Comment> list = commentDao.findByTwoPropertyLimited(
				ICommentDAO.TARGETID, targetId, ICommentDAO.RESOURCE+".id",
				resourceId, offset, pageSize);
		return vOPOTransformator.transferCommentToVOList(list);
	}

	
	
	public Integer getCommentSum(Integer targetId, String resourceId)
			throws SeeWorldException {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(ICommentDAO.TARGETID, targetId);
		params.put(ICommentDAO.RESOURCE, resourceId);
		return commentDao.getCount(params);
	}
	
	
	public Integer getCommentSum(String userId)
			throws SeeWorldException {
		Assert.notNull(userId);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(ICommentDAO.USER+".id", userId);
		return commentDao.getCount(params);
	}
	public List<CommentVO> viewPersonalCommentByPage(String userId,
			Integer offset, Integer pageSize) throws SeeWorldException {
		Assert.notNull(userId);
		List<Comment> list = commentDao.findByPropertyLimited(ICommentDAO.USER+".id",
				userId, offset, pageSize);
		return vOPOTransformator.transferCommentToVOList(list);
	}

	public List<CommentVO> findAllCommentsByPage(Integer offset, Integer pageSize) throws SeeWorldException{
		// TODO Auto-generated method stub
		String hql = "from Comment as model";
		List<Comment> list = commentDao.getListByPage(hql, offset, pageSize);
		return vOPOTransformator.transferCommentToVOList(list);
	}

	public Integer getCommentSum() throws SeeWorldException {
		// TODO Auto-generated method stub
		return commentDao.getCount(null);
	}

}
