package edu.tju.ina.seeworld.logic;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.vo.FriendVO;

/**
 * 完成好友关系的相关逻辑处理，建立好友关系，解除好友关系，
 * 判断是否为好友，以及查找所有好友
 * @author Uranus
 */
public interface IFriendLogic {
	/**
	 * 将两个用户加为好友
	 * 
	 * @param offerId
	 * @param theOtherId
	 */
	public void makeFriends(FriendVO friend) throws SeeWorldException;

	/**
	 * 判断两个用户是否是好友关系
	 * 
	 * @param offerId
	 * @param theOtherId
	 * @return
	 */
	public boolean areFriends(FriendVO friend) throws SeeWorldException;

	/**
	 * 解除两用户间的好友关系
	 * 
	 * @param offerId
	 * @param theOtherId
	 * @return
	 */
	public void endFriendship(FriendVO friend) throws SeeWorldException;

	/**
	 * 返回好友JSON对象
	 * 
	 * @param userId
	 * @return
	 */
	public JSONObject getFriendJson();
	/**
	 * 返回某一用户所有好友数量
	 * 
	 * @param userId
	 * @return
	 */
	public int getFriendSum(String userId) throws SeeWorldException;
	/**
	 * 分页获得好友列表
	 */
	public JSONArray findFriendsByPage(FriendVO friend,int offset,int pageSize) throws SeeWorldException;
}
