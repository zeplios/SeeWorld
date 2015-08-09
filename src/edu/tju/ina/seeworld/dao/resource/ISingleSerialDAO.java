package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.SingleSerial;

public interface ISingleSerialDAO extends IBaseHibernateDAO<SingleSerial> {
	public static final String SERIAL = "serial";
	public static final String EPISODE = "episode";
	public static final String PROCESS = "process";
	public static final String DELETE = "deleted";
	public static final String STATUS = "status";
	public static final String ADDTIME = "addTime";
	public static final String USER = "user";

	public static final String ORDER_BY_NATURE = "episode asc";
	public static final String ORDER_BY_ADDTIME = "ss.addTime desc,episode asc";
	public static final String ORDER_BY_RECOMMENDEDCOUNT = "recommendedCount desc,episode asc";
	public static final String ORDER_BY_COLLECTEDCOUNT = "collectedCount desc,episode asc";
	public static final String ORDER_BY_CLICKCOUNT = "clickCount desc,episode asc";

	/**
	 *根据剧集的整部的ID查找所有分集
	 * 
	 * @param mutiId
	 *            整部剧集的共同ID
	 * @return
	 */
	List<SingleSerial> findAllSingleSerialsBySerialId(Integer commonId,
			int offset, int length) throws SeeWorldException;

	public List<SingleSerial> findOrderedListByPropertyByPage(
			String propertyName, Object value,boolean status, String orderColumn, int offset,
			int length) throws SeeWorldException;

	/**
	 * 查询多媒体文件对象的满足某些集合属性条件的个体数量。例如某个categortyID的电影数量，某个演员的电影数量等
	 * 
	 * @param propertyName
	 *            集合属性的名称,如category,actors
	 * @param fieldName
	 *            集合属性中的具体属性名称,如category中的id,name
	 * @param value
	 *            对fieldName的约束值
	 * @param resourceId
	 *            资源文件类型ID
	 * @param status
	 *            是否通过审核
	 * @return
	 * @throws SeeWorldException
	 */
	public int getCountForSetProperty(String propertyName, String fieldName,
			Object value, boolean status) throws SeeWorldException;
	
}
