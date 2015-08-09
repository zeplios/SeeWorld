package edu.tju.ina.seeworld.logic;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

import edu.tju.ina.seeworld.dao.resource.IMultimediaDAO;
import edu.tju.ina.seeworld.dao.resource.ISingleSerialDAO;
import edu.tju.ina.seeworld.dao.user.IRecommendationDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.Multimedia;
import edu.tju.ina.seeworld.po.resource.SingleSerial;
import edu.tju.ina.seeworld.po.user.Recommendation;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.util.DateUtil;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.util.enums.MultimediaType;
import edu.tju.ina.seeworld.vo.RecommendationVO;

public class RecommendationLogic implements IRecommendationLogic {
	private static final Logger log = Logger
			.getLogger(RecommendationLogic.class.getName());

	private IUserDAO userDao;
	private IRecommendationDAO recommendationDao;
	private IMultimediaDAO multimediaDao;
	private ISingleSerialDAO singleSerialDao;
	private VOPOTransformator vOPOTransformator;

	public int getRecommendationSum(String sender_id) throws SeeWorldException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(IRecommendationDAO.SENDER, sender_id);
		return recommendationDao.getCount(params);
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public void setRecommendationDao(IRecommendationDAO recommendationDao) {
		this.recommendationDao = recommendationDao;
	}

	public void setMultimediaDao(IMultimediaDAO multimediaDao) {
		this.multimediaDao = multimediaDao;
	}

	public void delRecommendation(Integer id) throws SeeWorldException {
		Recommendation r = recommendationDao.findById(id);
		recommendationDao.delete(r);
	}

	public void recommend(RecommendationVO recommend) throws SeeWorldException {
		String[] receivers = recommend.getReceivers().split(",");
		log.info("There are " + receivers.length + " receivers");
		String sender_id = recommend.getSender_id();
		Integer targetId = recommend.getTargetId();
		String message = recommend.getMessage();
		User sender = userDao.findById(sender_id);
		Resource resource = new Resource(recommend.getResourceId());
		Boolean isRead = false;
		Timestamp addTime = DateUtil.getCurrentTimestamp();
		List<Recommendation> list;
		for (int i = 0; i < receivers.length; i++) {
			String receiver_id = receivers[i];
			User receiver = userDao.findById(receiver_id);
			Recommendation instance = new Recommendation();
			list = recommendationDao.findByThreeProperty(
					IRecommendationDAO.SENDER, sender,
					IRecommendationDAO.RECEIVER, receiver,
					IRecommendationDAO.TARGETID, targetId);
			if (list.size() > 0) {
				instance = list.get(0);
				instance.setMessage(message);
				instance.setAddTime(addTime);
				instance.setIsRead(isRead);
				instance.setResource(resource);
				recommendationDao.update(instance);
			} else {
				instance.setSender(sender);
				instance.setReceiver(receiver);
				instance.setMessage(message);
				instance.setTargetID(targetId);
				instance.setResource(resource);
				instance.setIsRead(isRead);
				instance.setAddTime(addTime);
				recommendationDao.save(instance);
				if (resource.getName().equals(MultimediaType.Serial.name())) {
					Multimedia media = multimediaDao.findById(targetId);
					media.setRecommendedCount(media.getRecommendedCount() + 1);
					multimediaDao.update(media);
				} else {
					SingleSerial singleSerial = singleSerialDao
							.findById(targetId);
					singleSerial.setRecommendedCount(singleSerial
							.getRecommendedCount() + 1);
					singleSerialDao.update(singleSerial);
				}
			}
		}
	}

	public void setRead(Integer id) throws SeeWorldException {
		Recommendation r = recommendationDao.findById(id);
		r.setIsRead(true);
		r.setAddTime(DateUtil.getCurrentTimestamp());
		recommendationDao.update(r);
	}

	public void setSingleSerialDao(ISingleSerialDAO singleSerialDao) {
		this.singleSerialDao = singleSerialDao;
	}

	public JSONArray getRecommendationByPage(String sender_id, int offset,
			int pagesize) throws SeeWorldException {
		String hql = "from Recommendation as model where model.sender=? order by addTime desc";
		List<Recommendation> allRecommendation = recommendationDao
				.getListByPage(hql, offset, pagesize, sender_id);
		JSONArray recommendationList = new JSONArray();
		for (Recommendation r : allRecommendation) {
			RecommendationVO recommendationVo = vOPOTransformator
					.transferRecommendationToRecommendationVO(r);
			recommendationList.add(recommendationVo);
		}
		log.info("fetched " + recommendationList.size() + " recommendations");
		return recommendationList;
	}

	public JSONArray getRecommendedByPage(String receiver_id, int offset,
			int pagesize) throws SeeWorldException {
		// Pagination pager = new Pagination();
		// pager.setLen(recommendationSum);
		// pager.setPagesize(SeeWorldConfigUtil.getIntConfigValue(Constant.COMMENTS_RECOMMENDATION_PER_PAGE));
		// pager.setCurrentpage(currentPage);
		// pager.setPagelist();
		String hql = "from Recommendation as model where model.receiver=? order by model.isRead, model.addTime desc group by model.isRead";
		List<Recommendation> allRecommendation = recommendationDao
				.getListByPage(hql, offset, pagesize, receiver_id);
		JSONArray recommendationList = new JSONArray();
		for (Recommendation r : allRecommendation) {
			RecommendationVO recommendationVo = vOPOTransformator
					.transferRecommendationToRecommendationVO(r);
			recommendationList.add(recommendationVo);
		}
		log.info("fetched " + recommendationList.size() + " recommendations");
		return recommendationList;
	}

	public Integer getNewRecommendedNum(String receiver_id)
			throws SeeWorldException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(IRecommendationDAO.RECEIVER, receiver_id);
		params.put(IRecommendationDAO.READ, false);
		return recommendationDao.getCount(params);
	}

	public JSONArray initNewRecommended(String receiver_id, int offset,
			int pagesize) throws SeeWorldException {
		// Pagination pager = new Pagination();
		// pager.setLen(recommendationSum);
		// pager.setCurrentpage(currentPage);
		// pager.setPagelist();
		String hql = "from Recommendation as model  where model.receiver=? and model.isRead=false order by model.addTime desc";
		List<Recommendation> list = recommendationDao.getListByPage(hql,
				offset, pagesize, receiver_id);
		JSONArray recommendationList = new JSONArray();
		for (Recommendation r : list) {
			RecommendationVO recommendationVo = vOPOTransformator
					.transferRecommendationToRecommendationVO(r);
			recommendationList.add(recommendationVo);
		}
		log.info("fetched " + recommendationList.size() + " recommendations");
		return recommendationList;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public Integer getRecommendedNum(String receiverId)
			throws SeeWorldException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(IRecommendationDAO.RECEIVER, receiverId);
		return recommendationDao.getCount(params);
	}
}
