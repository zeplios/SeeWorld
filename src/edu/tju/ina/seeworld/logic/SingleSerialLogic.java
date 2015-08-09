package edu.tju.ina.seeworld.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.tju.ina.seeworld.dao.resource.ISerialDAO;
import edu.tju.ina.seeworld.dao.resource.ISingleSerialDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.resource.Serial;
import edu.tju.ina.seeworld.po.resource.SingleSerial;

public class SingleSerialLogic {
	private ISingleSerialDAO singleSerialDao;
	private ISerialDAO serialDao;

	public ISerialDAO getSerialDao() {
		return serialDao;
	}

	public void setSerialDao(ISerialDAO serialDao) {
		this.serialDao = serialDao;
	}

	public void setSingleSerialDao(ISingleSerialDAO singleSerialDao) {
		this.singleSerialDao = singleSerialDao;
	}

	public void update(SingleSerial s) throws SeeWorldException {
		singleSerialDao.update(s);
	}

	public Integer save(SingleSerial s) throws SeeWorldException {
		return (Integer)singleSerialDao.save(s);
	}

	public void delete(SingleSerial s) throws SeeWorldException {
		singleSerialDao.delete(s);
	}

	public SingleSerial findByID(Integer id) throws SeeWorldException {
		return singleSerialDao.findById(id);
	}

	public List<SingleSerial> findRelativeSingleSerials(Integer commonId,
			int offset, int length) throws SeeWorldException {
		List<SingleSerial> list = new ArrayList<SingleSerial>();
		Serial s = serialDao.findById(commonId);
		for (Category c : s.getCategory()) {
			list.addAll(singleSerialDao.findOrderedListByPropertyByPage(
					ISerialDAO.CATEGORY, c.getId(), true,
					ISingleSerialDAO.ORDER_BY_ADDTIME, offset, length));
			list.remove(s);
			if (list.size() >= length)
				break;
		}
		return list.subList(0, list.size() >= length ? length : list.size());
	}

	public List<SingleSerial> findByAllBySeason(Integer commonId, int offset,
			int length) throws SeeWorldException {
		return singleSerialDao.findAllSingleSerialsBySerialId(commonId, offset,
				length);
	}

	public List<SingleSerial> findOrderByTime(int offset, int length)
			throws SeeWorldException {
		return singleSerialDao.findOrderedListByPropertyByPage("", "", true,
				ISingleSerialDAO.ORDER_BY_ADDTIME, offset, length);
	}

	public int getCount(Map<String, Object> map) throws SeeWorldException {
		return singleSerialDao.getCount(map);
	}

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
			Object value, boolean status) throws SeeWorldException {
		return singleSerialDao.getCountForSetProperty(propertyName, fieldName,
				value, status);
	}

	public List<SingleSerial> findOrderedListByPropertyByPage(
			String propertyName, Object value, boolean status,
			String orderColumn, int offset, int length)
			throws SeeWorldException {
		return singleSerialDao.findOrderedListByPropertyByPage(propertyName,
				value, status, orderColumn, offset, length);
	}
}
