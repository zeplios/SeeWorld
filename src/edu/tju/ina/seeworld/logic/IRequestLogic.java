package edu.tju.ina.seeworld.logic;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.vo.RequestVO;

public interface IRequestLogic {
	/**
	 * 添加请求
	 * 
	 * @param senderId
	 * @param reciverId
	 */
	public String addRequest(RequestVO request) throws SeeWorldException;
	/**
	 * 删除请求
	 * 
	 * @param senderId
	 * @param reciverId
	 */
	public String deleteRequest(RequestVO request) throws SeeWorldException;
	/**
	 * 获得请求列表
	 * 
	 * @param senderId
	 * @param reciverId
	 */
	public void showRequestList(RequestVO request) throws SeeWorldException;
	/**
	 * 获得请求JSON对象
	 * 
	 * @param senderId
	 * @param reciverId
	 */
	public JSONObject getRequestJson();
	/**
	 * 获得请求JSONPage对象
	 * 
	 * @param senderId
	 * @param reciverId
	 */
	public JSONObject getPageJson();
	/**
	 * 获得请求数目
	 * 
	 * @param senderId
	 * @param reciverId
	 */
	public int getRequestSum();
	/**
	 * 进入指定页
	 * 
	 * @param senderId
	 * @param reciverId
	 */
	public void gotoPage(RequestVO request,Integer requestSum,Integer currentPage) throws SeeWorldException;
	/**
	 * 获得请求JSONList对象
	 * 
	 * @param senderId
	 * @param reciverId
	 */
	public JSONArray getRequestList();
	/**
	 * 获得请求JSONList对象
	 * 
	 * @param senderId
	 * @param reciverId
	 */
	public int getRequestNum(RequestVO request) throws SeeWorldException;
}
