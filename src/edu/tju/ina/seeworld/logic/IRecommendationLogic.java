package edu.tju.ina.seeworld.logic;

import net.sf.json.JSONArray;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.vo.RecommendationVO;

/**
 * 完成电影推荐（分享）相关的逻辑操作
 * 
 * @author Uranus
 * 
 */
public interface IRecommendationLogic {

	/**
	 * 用户向一个或多个对象推荐某一资源
	 * 
	 * @param recommend
	 */
	public void recommend(RecommendationVO recommend) throws SeeWorldException;

	/**
	 * 将推荐状态设置为已读
	 */
	public void setRead(Integer id) throws SeeWorldException;

	/**
	 * 推荐者删除自己之前进行的推荐
	 * 
	 * @param useId
	 * @param id
	 */
	public void delRecommendation(Integer id) throws SeeWorldException;

	/**
	 * 获得自己推荐给别人的总数
	 * 
	 * @param recommend
	 * @return
	 */
	public int getRecommendationSum(String sender_id) throws SeeWorldException;

	/**
	 * 获得好友推荐的资源，分布方式
	 * 
	 * @param recommend
	 * @param recommendationSum
	 * @param currentPage
	 * @throws SeeWorldException
	 */
	public JSONArray getRecommendedByPage(String receiver_id, int offset,
			int pagesize) throws SeeWorldException;

	/**
	 * 获得自己推荐给别人的资源列表的分页类
	 * 
	 * @param recommend
	 * @param recommendationSum
	 * @param currentPage
	 * @throws SeeWorldException
	 */
	public JSONArray getRecommendationByPage(String sender_id, int offset,
			int pagesize) throws SeeWorldException;

	/**
	 * 返回别人推荐给自己的推荐数，总数，包括已读和未读
	 * 
	 * @param recommend
	 * @return
	 * @throws SeeWorldException
	 */
	public Integer getRecommendedNum(String receiver_id)
			throws SeeWorldException;

	/**
	 * 返回别人推荐给自己的未读推荐数
	 * 
	 * @param recommend
	 * @return
	 * @throws SeeWorldException
	 */
	public Integer getNewRecommendedNum(String receiver_id)
			throws SeeWorldException;

	/**
	 * 显示用户收到的新推荐,分页
	 * 
	 * @param recommend
	 * @throws SeeWorldException
	 */
	public JSONArray initNewRecommended(String receiver_id, int offset,
			int pagesize) throws SeeWorldException;
}
