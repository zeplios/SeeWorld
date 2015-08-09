package edu.tju.ina.seeworld.dao.resource;

// default package

import java.sql.Timestamp;
import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Video;
import edu.tju.ina.seeworld.util.IDAssistant;
import edu.tju.ina.seeworld.util.enums.MultimediaType;

/**
 * @see .Video
 * @author Uranus
 */
@SuppressWarnings("unchecked")
public class VideoDAO extends BaseHibernateDAO<Video> implements IVideoDAO {
	private IMultimediaDAO multimediaDao;
	private IDAssistant iDAssistant;
	private static final String TYPE = MultimediaType.Video.name();

	protected void initDao() {
		super.init(Video.class.getName());
	}

	public Video findTheFirstVideoInSections(Video v) throws SeeWorldException {
		if (v.getSectionNum() > 1) {
			List<Video> list = findByTwoProperty(IVideoDAO.TITLE, v.getTitle(),
					IVideoDAO.SECTION_NUM, 1);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return v;
	}

	public List<Video> findLatestList(int offset, int pageSize)
			throws SeeWorldException {

		try {
			return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
					"", "", TYPE, true, IMultimediaDAO.ORDER_BY_ADDTIME,
					offset, pageSize);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public List<Video> findRecommendedMost(int offset, int pageSize)
			throws SeeWorldException {

		try {
			return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
					"", "", TYPE, true,
					IMultimediaDAO.ORDER_BY_RECOMMENDEDCOUNT, offset, pageSize);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public List<Video> findClickMost(int offset, int pageSize)
			throws SeeWorldException {
		try {
			return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
					"", "", TYPE, true, IMultimediaDAO.ORDER_BY_CLICKCOUNT,
					offset, pageSize);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public List<Video> findCollectedMost(int offset, int pageSize)
			throws SeeWorldException {
		try {
			return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
					"", "", TYPE, true, IMultimediaDAO.ORDER_BY_COLLECTEDCOUNT,
					offset, pageSize);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public List<Video> findByAreaOrderByClickCount(Integer areaId, int offset,
			int length) throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.AREA + ".id", areaId, TYPE, true,
				IMultimediaDAO.ORDER_BY_CLICKCOUNT, offset, length);
	}

	public List<Video> findByAreaOrderByCollectedCount(Integer areaId,
			int offset, int length) throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.AREA + ".id", areaId, TYPE, true,
				IMultimediaDAO.ORDER_BY_COLLECTEDCOUNT, offset, length);
	}

	public List<Video> findByAreaOrderByName(Integer areaId, int offset,
			int length) throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.AREA + ".id", areaId, TYPE, true,
				IMultimediaDAO.ORDER_BY_NAME, offset, length);

	}

	public List<Video> findByAreaOrderByRecommendedCount(Integer areaId,
			int offset, int length) throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.AREA + ".id", areaId, TYPE, true,
				IMultimediaDAO.ORDER_BY_RECOMMENDEDCOUNT, offset, length);

	}

	public List<Video> findByAreaOrderByTime(Integer areaId, int offset,
			int length) throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.AREA + ".id", areaId, TYPE, true,
				IMultimediaDAO.ORDER_BY_ADDTIME, offset, length);
	}

	public List<Video> findByCategoryOrderByClickCount(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.CATEGORY, categoryId, TYPE, true,
				IMultimediaDAO.ORDER_BY_CLICKCOUNT, offset, length);
	}

	public List<Video> findByCategoryOrderByCollectedCount(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.CATEGORY, categoryId, TYPE, true,
				IMultimediaDAO.ORDER_BY_COLLECTEDCOUNT, offset, length);
	}

	public List<Video> findByCategoryOrderByName(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.CATEGORY, categoryId, TYPE, true,
				IMultimediaDAO.ORDER_BY_NAME, offset, length);
	}

	public List<Video> findByCategoryOrderByRecommendedCount(
			Integer categoryId, int offset, int length)
			throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.CATEGORY, categoryId, TYPE, true,
				IMultimediaDAO.ORDER_BY_RECOMMENDEDCOUNT, offset, length);
	}

	public List<Video> findByCategoryOrderByTime(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.CATEGORY, categoryId, TYPE, true,
				IMultimediaDAO.ORDER_BY_ADDTIME, offset, length);
	}

	public List<Video> findByYearOrderByName(String year, int offset, int length)
			throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.PULISHEDYEAR, year, TYPE, true,
				IMultimediaDAO.ORDER_BY_NAME, offset, length);
	}

	public List<Video> findByStatusOrderByName(boolean status, int offset,
			int length) throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.STATUS, status, TYPE, true,
				IMultimediaDAO.ORDER_BY_NAME, offset, length);
	}

	public List<String> getPulishedYearList() throws SeeWorldException {
		return multimediaDao.getPublishedYearList(iDAssistant
				.getResourceID(TYPE));
	}

	public void deleteUncompletedUpload(int videoId) throws SeeWorldException {
		// TODO
	}

	public void updateStatusAndProcess(Timestamp addTime, Integer userId,
			String name) throws SeeWorldException {
		// TODO Auto-generated method stub

	}

	public int getCountForSetProperty(String propertyName, String fieldName,
			Object value, boolean status) throws SeeWorldException {
		return multimediaDao.getCountForSetProperty(propertyName, fieldName,
				value, TYPE, status);
	}

	public List<Video> findOrderedListByPropertyByPage(
			String propertyName, Object value, boolean status,
			String orderColumn, int offset, int length)
			throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				propertyName, value, TYPE, status, orderColumn, offset, length);
	}

	public List<Video> findByYearOrderByTime(String year, int offset, int length)
			throws SeeWorldException {
		return (List<Video>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.PULISHEDYEAR, year, TYPE, true,
				IMultimediaDAO.ORDER_BY_ADDTIME, offset, length);
	}
	
	public void setMultimediaDao(IMultimediaDAO multimediaDao) {
		this.multimediaDao = multimediaDao;
	}

	public void setiDAssistant(IDAssistant iDAssistant) {
		this.iDAssistant = iDAssistant;
	}
}