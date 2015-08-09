package edu.tju.ina.seeworld.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.tju.ina.seeworld.dao.resource.IVideoDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.resource.Video;
import edu.tju.ina.seeworld.util.Constant;

public class VideoLogic {
	private IVideoDAO videoDao;
	private SettingLogic settingLogic;
	
	public void setVideoDao(IVideoDAO videoDao) {
		this.videoDao = videoDao;
	}

	public Integer add(Video v) throws SeeWorldException {
		return (Integer) videoDao.save(v);
	}

	public Video getVideoById(Integer id) throws SeeWorldException {
		return videoDao.findById(id);
	}

	
	public List<Video> getRelativeVideos(Integer id) throws SeeWorldException {

		List<Video> relativeVideoes = new ArrayList<Video>();
		Video video = videoDao.findById(id);
		int len = settingLogic.getIntConfigValue(Constant.RELATED_ITEMS_LENGTH);
		for (Category c : video.getCategory()) {
			relativeVideoes.addAll(videoDao.findByCategoryOrderByTime(c.getId(), 0, len));
			relativeVideoes.remove(video);
			if (relativeVideoes.size() >= len)
				break;
		}
		return relativeVideoes.subList(0, relativeVideoes.size() >= len ? len : relativeVideoes.size());

		//
		// List<KeyWordRelationship> kwrList1 = new
		// ArrayList<KeyWordRelationship>();
		// List<KeyWordRelationship> kwrList2 = new
		// ArrayList<KeyWordRelationship>();
		// String hql1 = "from KeyWordRelationship kwr where kwr.target.id=" +
		// Id
		// + "order by weight desc";
		// kwrList1 = keyWordRelationshipDao.findByQuery(hql1);
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
		// Video video = videoDao.findById(id);
		// relativeVideoes.add(video);
		// }
		// }
	}

	public List<String> getPublishedYearList() throws SeeWorldException {
		return videoDao.getPulishedYearList();
	}

	public List<Video> findLatestList(int offset, int pageSize)
			throws SeeWorldException {
		return videoDao.findLatestList(offset, pageSize);
	}

	public List<Video> findClickMost(int offset, int pageSize)
			throws SeeWorldException {
		return videoDao.findClickMost(offset, pageSize);
	}

	public void update(Video v) throws SeeWorldException {
		videoDao.update(v);
	}

	public void delete(Video v) throws SeeWorldException {
		videoDao.delete(v);
	}

	public List<Video> findByAreaOrderByClickCount(Integer areaId, int offset,
			int length) throws SeeWorldException {
		return videoDao.findByAreaOrderByClickCount(areaId, offset, length);
	}

	public List<Video> findByAreaOrderByCollectedCount(Integer areaId,
			int offset, int length) throws SeeWorldException {
		return videoDao.findByAreaOrderByCollectedCount(areaId, offset, length);
	}

	public List<Video> findByAreaOrderByName(Integer areaId, int offset,
			int length) throws SeeWorldException {
		return videoDao.findByAreaOrderByName(areaId, offset, length);

	}

	public List<Video> findByAreaOrderByRecommendedCount(Integer areaId,
			int offset, int length) throws SeeWorldException {
		return videoDao.findByAreaOrderByRecommendedCount(areaId, offset,
				length);
	}

	public List<Video> findByAreaOrderByTime(Integer areaId, int offset,
			int length) throws SeeWorldException {
		return videoDao.findByAreaOrderByTime(areaId, offset, length);
	}

	public List<Video> findByCategoryOrderByClickCount(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return videoDao.findByCategoryOrderByClickCount(categoryId, offset,
				length);
	}

	public List<Video> findByCategoryOrderByCollectedCount(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return videoDao.findByCategoryOrderByCollectedCount(categoryId, offset,
				length);
	}

	public List<Video> findByCategoryOrderByName(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return videoDao.findByCategoryOrderByName(categoryId, offset, length);
	}

	public List<Video> findByCategoryOrderByRecommendedCount(
			Integer categoryId, int offset, int length)
			throws SeeWorldException {
		return videoDao.findByCategoryOrderByRecommendedCount(categoryId,
				offset, length);
	}

	public List<Video> findByCategoryOrderByTime(Integer categoryId,
			int offset, int length) throws SeeWorldException {
		return videoDao.findByCategoryOrderByTime(categoryId, offset, length);
	}

	public List<Video> findByYearOrderByName(String year, int offset, int length)
			throws SeeWorldException {
		return videoDao.findByYearOrderByName(year, offset, length);
	}

	public List<Video> findByYearOrderByTime(String year, int offset, int length)
			throws SeeWorldException {
		return videoDao.findByYearOrderByTime(year, offset, length);
	}

	public Video findTheFirstVideoInSections(Video m) throws SeeWorldException {
		return videoDao.findTheFirstVideoInSections(m);
	}

	public List<Video> findByStatusOrderByName(boolean status, int offset,
			int length) throws SeeWorldException {
		return videoDao.findByStatusOrderByName(status, offset, length);
	}

	public List<Video> getVideoByName(String name) throws SeeWorldException {
		return videoDao.findByProperty(IVideoDAO.TITLE, name);
	}

	public int getCount(Map<String, Object> map) throws SeeWorldException {
		return videoDao.getCount(map);
	}

	public int getCountForSetProperty(String propertyName, String fieldName,
			Object value, boolean status) throws SeeWorldException {
		return videoDao.getCountForSetProperty(propertyName, fieldName, value, status);
	}

	public List<Video> findOrderedListByPropertyByPage(String propertyName,
			Object value, boolean status, String orderColumn, int offset,
			int length) throws SeeWorldException {
		return videoDao.findOrderedListByPropertyByPage(propertyName, value,
				status, orderColumn, offset, length);
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
	
}
