package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.SingleSerial;

public class SingleSerialDAO extends BaseHibernateDAO<SingleSerial> implements
		ISingleSerialDAO {
	protected void initDao() {
		super.init(SingleSerial.class.getName());
	}

	public List<SingleSerial> findAllSingleSerialsBySerialId(Integer commonId,
			int offset, int length) throws SeeWorldException {
		String hql = "from SingleSerial ss where ss.serial.id = ? order by " + ORDER_BY_NATURE;
		return getListByPage(hql, offset, length, commonId);
	}

	public List<SingleSerial> findOrderedListByPropertyByPage(
			String propertyName, Object value, boolean status,
			String orderColumn, int offset, int length)
			throws SeeWorldException {
		String hql = null;
		if (StringUtils.isNotBlank(propertyName)) {
			if (propertyName.equals(ISerialDAO.CATEGORY)) {
				hql = "select ss from SingleSerial ss join ss.serial sss join sss.category c where c.id=? and ss.status=? order by "
						+ orderColumn;
			} else {
				hql = "select ss  from SingleSerial ss where ss." + propertyName
						+ "=? and ss.status=? order by " + orderColumn;
			}
			return getListByPage(hql, offset, length, value, status);
		} else {
			hql = "select ss from SingleSerial ss where ss.status=? order by "
					+ orderColumn;
			return getListByPage(hql, offset, length, status);
		}
	}

	public int getCountForSetProperty(String propertyName, String fieldName,
			Object value, boolean status) throws SeeWorldException {
		final String hql = "select count(ss) from SingleSerial ss join ss."
				+ propertyName + " c where (c." + fieldName
				+ "=?) and ss.status=?";
		Object res = (Object) findByQuery(hql, new Object[] { value, status })
				.get(0);
		return Integer.valueOf(res.toString());
	}

}
