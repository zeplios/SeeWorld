package edu.tju.ina.seeworld.logic;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Status;
import edu.tju.ina.seeworld.vo.StatusVO;

/**
 * 用户状态（类微博)相关逻辑处理
 * @author Uranus
 *
 */
public interface IStatusLogic {
	
	/**
	 * 用户更新状态
	 * @param userId
	 * @param status
	 */
	public void updateStatus(StatusVO status) throws SeeWorldException;
	
	/**
	 * 返回用户的所有状态
	 * @param userId
	 * @return
	 */
	public List<Status> getAllStatus(StatusVO status) throws SeeWorldException;
	
	/**
	 * 删除用户的某一状态
	 * @param userId 用户ID
	 * @param statusId 状态ID
	 */
	public void delStatus(StatusVO status) throws SeeWorldException;
	/**
	 *实现翻页
	 * @param userId 用户ID
	 * @param statusId 状态ID
	 */
	public void gotoPage(Integer statusSum,Integer currentPage,String userId) throws SeeWorldException;
	/**
	 *获得某一状态
	 * @param userId 用户ID
	 * @param statusId 状态ID
	 */
	public void showStatus(StatusVO status) throws SeeWorldException;
	/**
	 *获得分页vo类
	 * @param userId 用户ID
	 * @param statusId 状态ID
	 */
	public JSONObject getPageJson() ;
	/**
	 *获得状态总数
	 * @param userId 用户ID
	 * @param statusId 状态ID
	 */
	public int getStatusSum();
	/**
	 *获得状态列表
	 * @param userId 用户ID
	 * @param statusId 状态ID
	 */
	public JSONArray getStatusList();
	/**
	 *获得状态JSON对象
	 * @param userId 用户ID
	 * @param statusId 状态ID
	 */
	public  JSONObject getJsonStatus();
}
