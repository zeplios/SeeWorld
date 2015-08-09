package edu.tju.ina.seeworld.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.tju.ina.seeworld.dao.resource.IMovieDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.resource.Movie;
import edu.tju.ina.seeworld.util.Constant;

public class MovieLogic {
	private IMovieDAO movieDao;
	private SettingLogic settingLogic;
	
	public void setMovieDao(IMovieDAO movieDao) {
		this.movieDao = movieDao;
	}

	public Movie getMovieById(Integer id) throws SeeWorldException {
		return movieDao.findById(id);
	}

	public void update(Movie m) throws SeeWorldException {
		movieDao.update(m);
	}

	public void delete(Movie m) throws SeeWorldException {
		movieDao.delete(m);
	}

	public Integer add(Movie m) throws SeeWorldException {
		return (Integer) movieDao.save(m);
	}

	public List<Movie> getRelativeMovies(Integer id) throws SeeWorldException {
		List<Movie> relativeMovies = new ArrayList<Movie>();
		Movie movie = movieDao.findById(id);
		int len = settingLogic.getIntConfigValue(Constant.RELATED_ITEMS_LENGTH);
		
		for (Category c : movie.getCategory()) {
			relativeMovies.addAll(movieDao.findByCategoryOrderByTime(c.getId(), 0, len));
			relativeMovies.remove(movie);
			if (relativeMovies.size() >= len) {
				break;
			}
		}
		// List<KeyWordRelationship> kwrList1 = null;
		// List<KeyWordRelationship> kwrList2 = null;
		// String hql1 = "from KeyWordRelationship kwr where kwr.target.id=" +
		// Id
		// + "order by weight desc";
		// kwrList1 = keyWordRelationshipDao.findByQuery(hql1);
		//
		// if (kwrList1 != null && kwrList1.size() > 0
		// && kwrList1.get(0).getKeyWord() != null) {
		// // 权重最大的ID
		// Integer keywordId = kwrList1.get(0).getKeyWord().getId();
		//
		// String hql2 = "from KeyWordRelationship kwr where kwr.keyWord.id="
		// + keywordId;
		// kwrList2 = keyWordRelationshipDao.findByQuery(hql2);
		//
		// for (int i = 0; i < kwrList2.size(); i++) {
		// Integer id = kwrList2.get(i).getTarget().getId();
		// Movie movie = movieDao.findById(id);
		// relativeMovies.add(movie);
		// }
		// }
		return relativeMovies.subList(0, relativeMovies.size() >= len ? len : relativeMovies.size());
	}

	public List<Movie> getRandomMovies(int limit) throws SeeWorldException {
		String hql = "from Movie as model order by rand()";
		return movieDao.getListByPage(hql, 0, limit);
	}
	
	public List<String> getPublishedYearList() throws SeeWorldException {
		return movieDao.getPulishedYearList();
	}

	public List<Movie> findLatestList(int offset, int pageSize)
			throws SeeWorldException {
		return movieDao.findLatestList(offset, pageSize);
	}

	public List<Movie> findClickMost(int offset, int pageSize)
			throws SeeWorldException {
		return movieDao.findClickMost(offset, pageSize);
	}
	
	public List<Movie> findRecommendedMost(int offset, int pageSize)
			throws SeeWorldException {
		return movieDao.findRecommendedMost(offset, pageSize);
	}

	public List<Movie> findByAreaOrderByClickCount(Integer areaId, int offset,
			int length) throws SeeWorldException {
		return movieDao.findByAreaOrderByClickCount(areaId, offset, length);
	}

	public List<Movie> findByAreaOrderByCollectedCount(Integer areaId,
			int offset, int length) throws SeeWorldException {
		return movieDao.findByAreaOrderByCollectedCount(areaId, offset, length);
	}

	public List<Movie> findByAreaOrderByName(Integer areaId, int offset,
			int length) throws SeeWorldException {
		return movieDao.findByAreaOrderByName(areaId, offset, length);

	}

	public List<Movie> findByAreaOrderByRecommendedCount(Integer areaId,
			int offset, int length) throws SeeWorldException {
		return movieDao.findByAreaOrderByRecommendedCount(areaId, offset,
				length);
	}

	public List<Movie> findByAreaOrderByTime(Integer areaId, int offset,
			int length) throws SeeWorldException {
		return movieDao.findByAreaOrderByTime(areaId, offset, length);
	}

	public List<Movie> findByCategoryOrderByClickCount(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return movieDao.findByCategoryOrderByClickCount(categoryId, offset,
				length);
	}

	public List<Movie> findByCategoryOrderByCollectedCount(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return movieDao.findByCategoryOrderByCollectedCount(categoryId, offset,
				length);
	}

	public List<Movie> findByCategoryOrderByName(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return movieDao.findByCategoryOrderByName(categoryId, offset, length);
	}

	public List<Movie> findByCategoryOrderByRecommendedCount(
			Integer categoryId, int offset, int length)
			throws SeeWorldException {
		return movieDao.findByCategoryOrderByRecommendedCount(categoryId,
				offset, length);
	}

	public List<Movie> findByCategoryOrderByTime(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return movieDao.findByCategoryOrderByTime(categoryId, offset, length);
	}

	public List<Movie> findByYearOrderByName(String year, int offset, int length)
			throws SeeWorldException {
		return movieDao.findByYearOrderByName(year, offset, length);
	}

	public List<Movie> findByYearOrderByTime(String year, int offset, int length)
			throws SeeWorldException {
		return movieDao.findByYearOrderByTime(year, offset, length);
	}

	public List<Movie> findByStatusOrderByName(boolean status, int offset,
			int length) throws SeeWorldException {
		return movieDao.findByStatusOrderByName(status, offset, length);
	}

	public List<Movie> getMovieByName(String name) throws SeeWorldException {
		return movieDao.findByProperty(IMovieDAO.TITLE, name);
	}

	public int getCount(Map<String, Object> map) throws SeeWorldException {
		return movieDao.getCount(map);
	}
	
	/**
	 * @author zhfch
	 * @param name如果模糊查询，name参数需要有%
	 * @return
	 * @throws SeeWorldException
	 */
	public List<Movie> getMovieByNameByPage(String name, int offset, int length) throws SeeWorldException {
		String title = "replace(model." + IMovieDAO.TITLE + ", '·', '')";
		String hql = "from Movie as model where " + title + " like ? order by model.addTime";
		return movieDao.getListByPage(hql, offset, length, name);
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
		return movieDao.getCountForSetProperty(propertyName, fieldName, value,
				status);
	}

	public List<Movie> findOrderedListByPropertyByPage(String propertyName,
			Object value, boolean status, String orderColumn, int offset,
			int length) throws SeeWorldException {
		return movieDao.findOrderedListByPropertyByPage(propertyName, value,
				status, orderColumn, offset, length);
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
	
}