package edu.tju.ina.seeworld.logic;

import java.io.Serializable;
import java.util.List;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Comment;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.vo.CommentVO;

public interface ICommentLogic {
	/**
	 * 获取所有的评论列表
	 * @return
	 * @throws SeeWorldException 
	 */
	public List<CommentVO> findAllCommentsByPage(Integer offset, Integer pageSize) throws SeeWorldException;
	/**
	 * gotoPage 分页浏览逻辑方法
	 * 
	 * @param itemSum
	 *            传入总共的评论条数
	 * @param currentPage
	 *            传入当前页(就是完成后的页)
	 * @param targetId
	 *            传入被评论对象的ID
	 * @param resourceID
	 *            传入被评论对象的资源类型ID
	 * @return 无返回值
	 */
	public List<CommentVO> findCommentsByPage(Integer targetId,
			String resourceId, Integer offset, Integer pageSize)
			throws SeeWorldException;

	/**
	 * addComment 增加评论逻辑方法
	 * 
	 * @param user 传入评论用户对象
	 * @param title 传入评论标题
	 * @param targetId 传入被评论对象的ID
	 * @param resourceID 传入被评论对象的资源类型ID
	 * @param content 传入评论正文
	 * @return true 评论成功 false 评论失败
	 */
	public CommentVO addComment(User user, Integer targetId, String resourceId,
			String title, String content, Integer replyTo)
			throws SeeWorldException;

	/**
	 * getTargetID 根据对象的ID获得实际评论对象ID
	 * 
	 * @return 返回实际对象ID（如果对象为电影则返回电影的第一部分所对应的ID，若为其他类型则返回当前ID）
	 */
	public Integer getTargetId(Integer id, String resourceId)
			throws SeeWorldException;

	/**
	 * 获取全部评论条数
	 * @return
	 * @throws SeeWorldException
	 */
	public Integer getCommentSum() throws SeeWorldException;
	
	/**
	 * getCommentSum 获得某一对象的评论总条数
	 * 
	 * @return 返回评论总条数
	 */
	public Integer getCommentSum(Integer targetId, String resourceId)
			throws SeeWorldException;

	/**
	 * 返回某一用户的评论数
	 * 
	 * @param userId
	 * @return
	 * @throws SeeWorldException
	 */
	public Integer getCommentSum(String userId) throws SeeWorldException;

	public Comment findById(Serializable id) throws SeeWorldException;

	public void deleteComment(Comment c) throws SeeWorldException;

	public void updateComment(Comment c) throws SeeWorldException;
	public List<CommentVO> viewPersonalCommentByPage(String userId,
			Integer offset, Integer pageSize) throws SeeWorldException;

	public int support(Integer commentId) throws SeeWorldException;

	public int object(Integer commentId) throws SeeWorldException;
}
