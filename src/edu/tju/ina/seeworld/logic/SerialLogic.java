package edu.tju.ina.seeworld.logic;

import java.util.List;
import java.util.Map;

import edu.tju.ina.seeworld.dao.resource.ISerialDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Serial;

public class SerialLogic {
	private ISerialDAO serialDao;

	public void setSerialDao(ISerialDAO serialDao) {
		this.serialDao = serialDao;
	}

	public void update(Serial s) throws SeeWorldException {
		serialDao.update(s);
	}

	public Integer add(Serial s) throws SeeWorldException {
		return (Integer)serialDao.save(s);
	}

	public void delete(Serial s) throws SeeWorldException {
		serialDao.delete(s);
	}

	public Serial findByID(Integer id) throws SeeWorldException {
		return serialDao.findById(id);
	}
	
	public List<Serial> getRandomSerials(int limit) throws SeeWorldException {
		String hql = "from Serial as model order by rand()";
		return serialDao.getListByPage(hql, 0, limit);
	}

	public List<Serial> findByCategoryOrderByName(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage(ISerialDAO.CATEGORY,
				categoryId, ISerialDAO.ORDER_BY_NAME, offset, length);
	}

	public List<Serial> findByCategoryOrderByTime(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage(ISerialDAO.CATEGORY,
				categoryId, ISerialDAO.ORDER_BY_TIME, offset, length);
	}

	public List<Serial> findByNameOrderByTime(String name, int offset,
			int length) throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage(ISerialDAO.TITLE,
				name, ISerialDAO.ORDER_BY_TIME, offset, length);
	}

	public List<Serial> findByNameOrderBySeason(String name, int offset,
			int length) throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage(ISerialDAO.TITLE,
				name, ISerialDAO.ORDER_BY_SEASON, offset, length);
	}

	public List<Serial> findByAreaOrderByName(Integer areaAndCountryId,
			int offset, int length) throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage(ISerialDAO.AREA,
				areaAndCountryId, ISerialDAO.ORDER_BY_NAME, offset, length);
	}

	public List<Serial> findByAreaOrderByTime(Integer areaAndCountryId,
			int offset, int length) throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage(ISerialDAO.AREA,
				areaAndCountryId, ISerialDAO.ORDER_BY_TIME, offset, length);
	}

	public List<Serial> findByAreaOrderByClickCount(Integer areaAndCountryId,
			int offset, int length) throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage(ISerialDAO.AREA,
				areaAndCountryId, ISerialDAO.ORDER_BY_CLICKCOUNT, offset,
				length);
	}

	public List<Serial> findByYearOrderByName(String publishedYear, int offset,
			int length) throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage(
				ISerialDAO.PULISHEDYEAR, publishedYear,
				ISerialDAO.ORDER_BY_NAME, offset, length);
	}

	public List<Serial> findByYearOrderByTime(String publishedYear, int offset,
			int length) throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage(
				ISerialDAO.PULISHEDYEAR, publishedYear,
				ISerialDAO.ORDER_BY_TIME, offset, length);
	}

	public List<Serial> findByAddTime(int offset, int length)
			throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage("", "",
				ISerialDAO.ORDER_BY_TIME, offset, length);
	}

	public List<Serial> findByClickCount(int offset, int length)
			throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage("", "",
				ISerialDAO.ORDER_BY_CLICKCOUNT, offset, length);
	}

	public List<Serial> findByRecommendedCount(int offset, int length)
			throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage("", "",
				ISerialDAO.ORDER_BY_RECOMMENDEDCOUNT, offset, length);
	}

	public List<Serial> findByCollectCount(int offset, int length)
			throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage("", "",
				ISerialDAO.ORDER_BY_COLLECTEDCOUNT, offset, length);
	}

	public int getCount(Map<String, Object> map) throws SeeWorldException {
		return serialDao.getCount(map);
	}

	/**
	 * 查询满足某些集合属性条件的电影个体数量。例如某个categortyID的电影数量，某个演员的电影数量等
	 * 
	 * @param propertyName 集合属性的名称,如category,actors
	 * @param fieldName 集合属性中的具体属性名称,如category中的id,name
	 * @param value 对fieldName的约束值
	 * @param resourceId 资源文件类型ID
	 * @param status 是否通过审核
	 */
	public int getCountForSetProperty(String propertyName, String fieldName,
			Object value) throws SeeWorldException {
		return serialDao.getCountForSetProperty(propertyName, fieldName, value);
	}

	public List<Serial> findByPropertyByPage(String propertyName,
			Object value, String orderColumn, int offset, int length)
			throws SeeWorldException {
		return serialDao.findOrderedListByPropertyByPage(propertyName, value,
				orderColumn, offset, length);
	}

	/**
	 * 返回所以电视的发行年列表
	 * 
	 * @return
	 * @throws SeeWorldException
	 */
	public List<String> getPublishedYearList() throws SeeWorldException {
		return serialDao.getPublishedYearList();
	}
}
