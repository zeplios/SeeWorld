package edu.tju.ina.seeworld.logic;

import java.io.Serializable;
import java.util.List;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Collection;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.vo.CollectionVO;

public interface ICollectionLogic {

	/**
	 * findCollectionsOfResourceByPage 查找收藏的逻辑方法，如果resourceId为null，
	 * 刚返回所有类型的收藏结果
	 * @param targetUser 收藏人
	 * @param targetUser 传入收藏用户的用户Id
	 * @param offset 起始值
	 * @param pageSize 一页的容量
	 */
	public List<CollectionVO> findCollectionsOfResourceByPage(String targetUserId,
			Integer offset, Integer pageSize, String resourceId)
			throws SeeWorldException;

	/**
	 * 当resourceId为空，返回该用户收藏的总数；当resourceId不为空，则返回该用户此类型收藏的总数
	 * 
	 * @param userId
	 * @param resourceId
	 * @return
	 * @throws SeeWorldException
	 */
	public Integer getCollectionSum(String userId, String resourceId)
			throws SeeWorldException;

	/**
	 * 判断某一资源是否被收藏
	 * 
	 * @param targetid
	 * @param resourceId
	 * @param userId
	 * @return
	 * @throws SeeWorldException
	 */
	public boolean isAlreadyCollected(int targetId, String resourceId, String userId)
			throws SeeWorldException;

	/**
	 * deleteCollection 删除收藏
	 * 
	 * @param deleteId 传入收藏对象Id
	 * @return true 成功 false 失败
	 */
	public void deleteCollection(Integer id) throws SeeWorldException;
	public Collection findById(Serializable id) throws SeeWorldException;
	public void addCollection(Integer objectId, String resourceId, User user)
			throws SeeWorldException;
}
