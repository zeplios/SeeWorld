package edu.tju.ina.seeworld.dao.resource;

import java.sql.Timestamp;
import java.util.List;

import edu.tju.ina.seeworld.dao.common.IBaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Video;

public interface IVideoDAO extends IBaseHibernateDAO<Video> {
	public static final String TITLE = "title";
	public static final String SECTION_NUM = "sectionNum";

	public List<Video> findByStatusOrderByName(boolean status, int offset,
			int length) throws SeeWorldException;

	public void updateStatusAndProcess(Timestamp addTime, Integer userId,
			String name) throws SeeWorldException;

	public void deleteUncompletedUpload(int videoId) throws SeeWorldException;

	public List<String> getPulishedYearList() throws SeeWorldException;

	public List<Video> findLatestList(int offset, int pageSize)
			throws SeeWorldException;

	public List<Video> findClickMost(int offset, int pageSize)
			throws SeeWorldException;

	public List<Video> findRecommendedMost(int offset, int pageSize)
			throws SeeWorldException;

	public List<Video> findCollectedMost(int offset, int pageSize)
			throws SeeWorldException;

	public List<Video> findByCategoryOrderByName(Integer categoryId,
			int offset, int length) throws SeeWorldException;

	public List<Video> findByCategoryOrderByTime(Integer categoryId,
			int offset, int length) throws SeeWorldException;

	public List<Video> findByCategoryOrderByCollectedCount(Integer categoryId,
			int offset, int length) throws SeeWorldException;

	public List<Video> findByCategoryOrderByRecommendedCount(
			Integer categoryId, int offset, int length)
			throws SeeWorldException;

	public List<Video> findByCategoryOrderByClickCount(Integer categoryId,
			int offset, int length) throws SeeWorldException;

	public List<Video> findByAreaOrderByName(Integer araeId, int offset,
			int length) throws SeeWorldException;

	public List<Video> findByAreaOrderByTime(Integer araeId, int offset,
			int length) throws SeeWorldException;

	public List<Video> findByAreaOrderByCollectedCount(Integer araeId,
			int offset, int length) throws SeeWorldException;

	public List<Video> findByAreaOrderByRecommendedCount(Integer araeId,
			int offset, int length) throws SeeWorldException;

	public List<Video> findByAreaOrderByClickCount(Integer araeId, int offset,
			int length) throws SeeWorldException;

	public List<Video> findByYearOrderByName(String year, int offset, int length)
			throws SeeWorldException;
	/**
	 * 按addtime排序
	 * @param year
	 * @param offset
	 * @param length
	 * @return
	 * @throws SeeWorldException
	 */
	public List<Video> findByYearOrderByTime(String year, int offset, int length)
	throws SeeWorldException;
	public int getCountForSetProperty(String propertyName, String fieldName,
			Object value, boolean status) throws SeeWorldException;

	public Video findTheFirstVideoInSections(Video v) throws SeeWorldException;

	public List<Video> findOrderedListByPropertyByPage(
			String propertyName, Object value, boolean status,
			String orderColumn, int offset, int length)
			throws SeeWorldException;
}
