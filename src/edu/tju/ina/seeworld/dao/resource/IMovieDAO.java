package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Movie;

public interface IMovieDAO extends IBaseHibernateDAO<Movie> {
	public static final String TITLE = "title";
	public static final String SECTION_NUM = "sectionNum";

	/**
	 * 查找最近更新电影列表
	 * 
	 * @return
	 */
	public List<Movie> findLatestList(int offset, int pageSize)
			throws SeeWorldException;

	/**
	 * 查找最近所有电影中点击率最高列表
	 * 
	 * @return
	 */
	public List<Movie> findClickMost(int offset, int pageSize)
			throws SeeWorldException;

	public List<Movie> findRecommendedMost(int offset, int pageSize)
			throws SeeWorldException;

	public List<Movie> findCollectedMost(int offset, int pageSize)
			throws SeeWorldException;

	public List<String> getPulishedYearList() throws SeeWorldException;

	public List<Movie> findByStatusOrderByName(boolean status, int offset,
			int length) throws SeeWorldException;

	public List<Movie> findByCategoryOrderByName(Integer categoryId,
			int offset, int length) throws SeeWorldException;

	public List<Movie> findByCategoryOrderByTime(Integer categoryId,
			int offset, int length) throws SeeWorldException;

	public List<Movie> findByCategoryOrderByCollectedCount(Integer categoryId,
			int offset, int length) throws SeeWorldException;

	public List<Movie> findByCategoryOrderByRecommendedCount(
			Integer categoryId, int offset, int length)
			throws SeeWorldException;

	public List<Movie> findByCategoryOrderByClickCount(Integer categoryId,
			int offset, int length) throws SeeWorldException;

	public List<Movie> findByAreaOrderByName(Integer areaId, int offset,
			int length) throws SeeWorldException;

	public List<Movie> findByAreaOrderByTime(Integer areaId, int offset,
			int length) throws SeeWorldException;

	public List<Movie> findByAreaOrderByCollectedCount(Integer areaId,
			int offset, int length) throws SeeWorldException;

	public List<Movie> findByAreaOrderByRecommendedCount(Integer areaId,
			int offset, int length) throws SeeWorldException;

	public List<Movie> findByAreaOrderByClickCount(Integer areaId, int offset,
			int length) throws SeeWorldException;

	public List<Movie> findByYearOrderByName(String year, int offset, int length)
			throws SeeWorldException;
	public List<Movie> findByYearOrderByTime(String year, int offset, int length)
	throws SeeWorldException;
	/**
	 * 查询满足某些集合属性条件的电影个体数量。例如某个categortyID的电影数量，某个演员的电影数量等
	 * 
	 * @param propertyName
	 *            集合属性的名称,如category,actors
	 *@param fieldName
	 *            集合属性中的具体属性名称,如category中的id,name
	 *@param value
	 *            对fieldName的约束值
	 *@param resourceId
	 *            资源文件类型ID
	 *@param status
	 *            是否通过审核
	 */
	public int getCountForSetProperty(String propertyName, String fieldName,
			Object value, boolean status) throws SeeWorldException;

	public List<Movie> findOrderedListByPropertyByPage(
			String propertyName, Object value,boolean status, String orderColumn, int offset, int length)
			throws SeeWorldException;
}
