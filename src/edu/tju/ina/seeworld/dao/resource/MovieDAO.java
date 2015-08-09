package edu.tju.ina.seeworld.dao.resource;

// default package

import java.util.List;

import edu.tju.ina.seeworld.dao.common.BaseHibernateDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Movie;
import edu.tju.ina.seeworld.util.IDAssistant;
import edu.tju.ina.seeworld.util.enums.MultimediaType;

/**
 * @see .Movie
 * @author Uranus
 */
@SuppressWarnings("unchecked")
public class MovieDAO extends BaseHibernateDAO<Movie> implements IMovieDAO {

	private IMultimediaDAO multimediaDao;
	private IDAssistant iDAssistant;
	private static final String TYPE = MultimediaType.Movie.name();

	protected void initDao() {
		super.init(Movie.class.getName());
	}

	public void setMultimediaDao(IMultimediaDAO multimediaDao) {
		this.multimediaDao = multimediaDao;
	}

	public void setiDAssistant(IDAssistant iDAssistant) {
		this.iDAssistant = iDAssistant;
	}

	public List<Movie> findLatestList(int offset, int pageSize)
			throws SeeWorldException {
		try {
			return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
					"", "", TYPE, true, IMultimediaDAO.ORDER_BY_ADDTIME,
					offset, pageSize);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public List<Movie> findClickMost(int offset, int pageSize)
			throws SeeWorldException {
		try {
			return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
					"", "", TYPE, true, IMultimediaDAO.ORDER_BY_CLICKCOUNT,
					offset, pageSize);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public List<Movie> findCollectedMost(int offset, int pageSize)
			throws SeeWorldException {
		try {
			return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
					"", "", TYPE, true, IMultimediaDAO.ORDER_BY_COLLECTEDCOUNT,
					offset, pageSize);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public List<Movie> findRecommendedMost(int offset, int pageSize)
			throws SeeWorldException {
		try {
			return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
					"", "", TYPE, true,
					IMultimediaDAO.ORDER_BY_RECOMMENDEDCOUNT, offset, pageSize);
		} catch (Exception re) {
			throw new SeeWorldException(re);
		}
	}

	public List<Movie> findByAreaOrderByClickCount(Integer areaId, int offset,
			int length) throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.AREA , areaId, TYPE, true,
				IMultimediaDAO.ORDER_BY_CLICKCOUNT, offset, length);
	}

	public List<Movie> findByAreaOrderByCollectedCount(Integer areaId,
			int offset, int length) throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.AREA , areaId, TYPE, true,
				IMultimediaDAO.ORDER_BY_COLLECTEDCOUNT, offset, length);
	}

	public List<Movie> findByAreaOrderByName(Integer areaId, int offset,
			int length) throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.AREA , areaId, TYPE, true,
				IMultimediaDAO.ORDER_BY_NAME, offset, length);

	}

	public List<Movie> findByAreaOrderByRecommendedCount(Integer areaId,
			int offset, int length) throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.AREA , areaId, TYPE, true,
				IMultimediaDAO.ORDER_BY_RECOMMENDEDCOUNT, offset, length);

	}

	public List<Movie> findByAreaOrderByTime(Integer areaId, int offset,
			int length) throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.AREA , areaId, TYPE, true,
				IMultimediaDAO.ORDER_BY_ADDTIME, offset, length);
	}

	public List<Movie> findByCategoryOrderByClickCount(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.CATEGORY, categoryId, TYPE, true,
				IMultimediaDAO.ORDER_BY_CLICKCOUNT, offset, length);
	}

	public List<Movie> findByCategoryOrderByCollectedCount(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.CATEGORY, categoryId, TYPE, true,
				IMultimediaDAO.ORDER_BY_COLLECTEDCOUNT, offset, length);
	}

	public List<Movie> findByCategoryOrderByName(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.CATEGORY, categoryId, TYPE, true,
				IMultimediaDAO.ORDER_BY_NAME, offset, length);
	}

	public List<Movie> findByCategoryOrderByRecommendedCount(
			Integer categoryId, int offset, int length)
			throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.CATEGORY, categoryId, TYPE, true,
				IMultimediaDAO.ORDER_BY_RECOMMENDEDCOUNT, offset, length);
	}

	public List<Movie> findByCategoryOrderByTime(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.CATEGORY, categoryId, TYPE, true,
				IMultimediaDAO.ORDER_BY_ADDTIME, offset, length);
	}

	public List<Movie> findByYearOrderByName(String year, int offset, int length)
			throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.PULISHEDYEAR, year, TYPE, true,
				IMultimediaDAO.ORDER_BY_NAME, offset, length);
	}

	public List<String> getPulishedYearList() throws SeeWorldException {
		return multimediaDao.getPublishedYearList(iDAssistant
				.getResourceID(TYPE));
	}

	public List<Movie> findByStatusOrderByName(boolean status, int offset,
			int length) throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.STATUS, status, TYPE, true,
				IMultimediaDAO.ORDER_BY_NAME, offset, length);
	}

	public int getCountForSetProperty(String propertyName, String fieldName,
			Object value, boolean status) throws SeeWorldException {
		return multimediaDao.getCountForSetProperty(propertyName, fieldName,
				value, TYPE, status);
	}

	public List<Movie> findOrderedListByPropertyByPage(
			String propertyName, Object value, boolean status,
			String orderColumn, int offset, int length)
			throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				propertyName, value, TYPE, status, orderColumn, offset, length);
	}

	public List<Movie> findByYearOrderByTime(String year, int offset, int length)
			throws SeeWorldException {
		return (List<Movie>) multimediaDao.findOrderedListByPropertyByPage(
				IMultimediaDAO.PULISHEDYEAR, year, TYPE, true,
				IMultimediaDAO.ORDER_BY_ADDTIME, offset, length);
	}

}