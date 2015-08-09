package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Serial;

public interface ISerialDAO extends IBaseHibernateDAO<Serial> {
	public static final String TITLE = "title";
	public static final String AREA = "areaAndCountry";
	public static final String RESOURCE = "resource";
	public static final String SEASONS = "seasons";
	public static final String CATEGORY = "category";
	public static final String SEASON = "season";
	public static final String PULISHEDYEAR = "publishedYear";

	public static final String ORDER_BY_TIME = "addTime desc,season asc";
	public static final String ORDER_BY_NAME = "title,season asc";
	public static final String ORDER_BY_SEASON = "season asc";
	public static final String ORDER_BY_RECOMMENDEDCOUNT = "recommendedCount desc,season asc";
	public static final String ORDER_BY_COLLECTEDCOUNT = "collectedCount desc,season asc";
	public static final String ORDER_BY_CLICKCOUNT = "clickCount desc,season asc";

	/**
	 * 查找某一季、某一部剧集下的全部单集电视剧
	 * 
	 * @param commonId
	 * @param season
	 * @return
	 * @throws SeeWorldException
	 */
	public Serial findSerialByNameAndSeason(String name, Integer season)
			throws SeeWorldException;
	public List<String> getPublishedYearList() throws SeeWorldException;

	public List<Serial> findOrderedListByPropertyByPage(String propertyName,
			Object value, String orderColumn, int offset,
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
	 * @return
	 * @throws SeeWorldException
	 */
	public int getCountForSetProperty(String propertyName, String fieldName,
			Object value) throws SeeWorldException;
}
