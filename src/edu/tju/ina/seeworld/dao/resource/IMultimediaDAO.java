package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Multimedia;

public interface IMultimediaDAO extends IBaseHibernateDAO<Multimedia> {
	public static final String ID = "id";
	public static final String AREA = "areaAndCountry";
	public static final String TITLE = "title";
	public static final String IMAGE = "image";
	public static final String PATH = "path";
	public static final String INTRODUCTION = "introduction";
	public static final String SIZE = "size";
	public static final String PULISHEDYEAR = "publishedYear";
	public static final String PROCESS = "process";
	public static final String DELETE = "deleted";
	public static final String RESOURCE = "resource";
	public static final String STATUS = "status";
	public static final String ADDTIME = "addTime";
	public static final String CATEGORY = "category";
	public static final String SERIAL = "serial";

	public static final String ORDER_BY_NAME = "title asc";
	public static final String ORDER_BY_ADDTIME = "addTime desc";
	public static final String ORDER_BY_RECOMMENDEDCOUNT = "recommendedCount desc";
	public static final String ORDER_BY_COLLECTEDCOUNT = "collectedCount desc";
	public static final String ORDER_BY_CLICKCOUNT = "clickCount desc";

	public List<? extends Multimedia> findByName(Object name)
			throws SeeWorldException;

	public List<? extends Multimedia> findBySize(Object size)
			throws SeeWorldException;

	public List<? extends Multimedia> findByProcess(Object process)
			throws SeeWorldException;

	public List<? extends Multimedia> findByDelete(Object delete)
			throws SeeWorldException;

	public List<? extends Multimedia> findByResourceType(Object resourceType)
			throws SeeWorldException;

	public List<String> getPublishedYearList(String resourceId)
			throws SeeWorldException;

	public List<? extends Multimedia> findByListAndCondition(
			final List<Integer> MultimeidaIdList, final String condition,
			final int start, final int maxsize) throws SeeWorldException;

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
			Object value, Object resourceName, boolean status)
			throws SeeWorldException;

	public List<? extends Multimedia> findOrderedListByPropertyByPage(
			String propertyName, Object value, Object resourceName,
			boolean status, String orderColumn, int offset, int length)
			throws SeeWorldException;
}
