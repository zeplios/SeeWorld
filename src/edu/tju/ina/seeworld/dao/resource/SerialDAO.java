package edu.tju.ina.seeworld.dao.resource;

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Serial;
import edu.tju.ina.seeworld.util.IDAssistant;
import edu.tju.ina.seeworld.util.enums.MultimediaType;

public class SerialDAO extends BaseHibernateDAO<Serial> implements ISerialDAO {
	
	private IMultimediaDAO multimediaDao;
	private IDAssistant iDAssistant;
	private static final String TYPE = MultimediaType.Serial.name();
	
	protected void initDao() {
		super.init(Serial.class.getName());
	}

	@SuppressWarnings("unchecked")
	public List<Serial> findOrderedListByPropertyByPage(String propertyName,
			Object value, String orderColumn, int offset,
			int length) throws SeeWorldException {
		return (List<Serial>) multimediaDao.findOrderedListByPropertyByPage(
					propertyName, value, TYPE, true, orderColumn, offset, length);
	}

	public int getCountForSetProperty(String propertyName, String fieldName,
			Object value) throws SeeWorldException {
		final String hql = "select count(s) from Serial s join s."
				+ propertyName + " c where (c." + fieldName
				+ "=?) ";
		Object res = (Object) findByQuery(hql, new Object[] { value})
				.get(0);
		return Integer.valueOf(res.toString());
	}

	public List<String> getPublishedYearList() throws SeeWorldException {
		return multimediaDao.getPublishedYearList(iDAssistant
				.getResourceID(TYPE));
	}
	
	public Serial findSerialByNameAndSeason(String name, Integer season)
			throws SeeWorldException {
		List<Serial> list = findByTwoProperty(ISerialDAO.TITLE, name,
				ISerialDAO.SEASON, name);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	public void setMultimediaDao(IMultimediaDAO multimediaDao) {
		this.multimediaDao = multimediaDao;
	}

	public void setiDAssistant(IDAssistant iDAssistant) {
		this.iDAssistant = iDAssistant;
	}
}
