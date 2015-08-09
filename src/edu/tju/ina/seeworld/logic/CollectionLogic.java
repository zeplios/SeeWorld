package edu.tju.ina.seeworld.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import edu.tju.ina.seeworld.dao.resource.IMovieDAO;
import edu.tju.ina.seeworld.dao.resource.IMultimediaDAO;
import edu.tju.ina.seeworld.dao.resource.ISerialDAO;
import edu.tju.ina.seeworld.dao.resource.ISingleSerialDAO;
import edu.tju.ina.seeworld.dao.resource.IVideoDAO;
import edu.tju.ina.seeworld.dao.user.ICollectionDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.resource.Movie;
import edu.tju.ina.seeworld.po.resource.Multimedia;
import edu.tju.ina.seeworld.po.resource.Serial;
import edu.tju.ina.seeworld.po.resource.SingleSerial;
import edu.tju.ina.seeworld.po.resource.Video;
import edu.tju.ina.seeworld.po.user.Collection;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.util.DateUtil;
import edu.tju.ina.seeworld.util.IDAssistant;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.util.enums.MultimediaType;
import edu.tju.ina.seeworld.vo.CollectionVO;

/**
 * Title: CollectLogic Created on 2010-3-26 Description: The logical class of
 * Collect
 * 
 * @author wzh
 * @version 1.0
 */

public class CollectionLogic implements ICollectionLogic {
	private INewThingsLogic newThingsLogic;
	private ICollectionDAO collectionDao;
	private IMovieDAO movieDao;
	private IDAssistant iDAssistant;
	private IMultimediaDAO multimediaDao;
	private ISingleSerialDAO singleSerialDao;
	private ISerialDAO serialDao;
	private IVideoDAO videoDao;
	private VOPOTransformator vOPOTransformator;

	public Integer getCollectionSum(String userId, String resourceId)
			throws SeeWorldException {
		Assert.notNull(userId);
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put(ICollectionDAO.USER, userId);
		if (StringUtils.isNotBlank(resourceId)) {
			params.put(ICollectionDAO.RESOURCE, resourceId);
		}
		return collectionDao.getCount(params);
	}

	public List<CollectionVO> findCollectionsOfResourceByPage(String targetUserId,
			Integer offset, Integer pageSize, String resourceId)
			throws SeeWorldException {
		String hql = "";
		if (StringUtils.isNotBlank(resourceId)) {
			hql = "from Collection where user.id='" + targetUserId
					+ "' and resource.id='" + resourceId
					+ "' order by addTime desc";
		} else {
			hql = "from Collection where user.id='" + targetUserId
					+ "' order by addTime desc";
		}
		
		List<Collection> collections = collectionDao.getListByPage(hql, offset, pageSize);
		List<CollectionVO> collectionList = new ArrayList<CollectionVO>();
		
		for (Collection c : collections) {
			collectionList.add(vOPOTransformator.transferCollectionToVO(c));
		}
		return collectionList;
	}

	public void deleteCollection(Integer deleteId) throws SeeWorldException {
		Collection deleteCollection = collectionDao.findById(deleteId);
		String resourceId = deleteCollection.getResource().getId();
		Integer targetId = deleteCollection.getTargetID();
		String resourceName = iDAssistant.getResourceName(resourceId);
		if (resourceName.equals(MultimediaType.SingleSerial.name())) {
			SingleSerial ss = singleSerialDao.findById(targetId);
			Serial s = ss.getSerial();
			ss.setCollectedCount(ss.getCollectedCount() - 1);
			singleSerialDao.update(ss);
			s.setCollectedCount(s.getCollectedCount() - 1);
			serialDao.update(s);
		} else {
			Multimedia m = multimediaDao.findById(targetId);
			m.setCollectedCount(m.getCollectedCount() - 1 < 0 ? 0 : m.getCollectedCount() - 1);
			multimediaDao.update(m);
		}
		collectionDao.delete(deleteCollection);
	}

	public void addCollection(Integer objectId, String resourceId, User user)
			throws SeeWorldException {
		String name = iDAssistant.getResourceName(resourceId);
		Collection collection = new Collection();
		Resource resource = new Resource();
		resource.setId(resourceId);
		collection.setResource(resource);
		collection.setUser(user);
		if (name.equals(MultimediaType.Movie.name())) {
			Movie multimedia = movieDao.findById(objectId);
			collection.setTargetID(multimedia.getId());
			multimedia.setCollectedCount(multimedia.getCollectedCount() + 1);
			movieDao.update(multimedia);
		} else {
			if (name.equals(MultimediaType.SingleSerial.name())) {
				SingleSerial singleSerial = singleSerialDao.findById(objectId);
				singleSerial
						.setCollectedCount(singleSerial.getCollectedCount() + 1);
				singleSerialDao.update(singleSerial);
				Serial s = singleSerial.getSerial();
				s.setCollectedCount(s.getCollectedCount() + 1);
				serialDao.update(s);
			} else {
				Video v = videoDao.findById(objectId);
				v.setCollectedCount(v.getCollectedCount() + 1);
				videoDao.update(v);
			}
			collection.setTargetID(objectId);
		}
		collection.setAddTime(DateUtil.getCurrentTimestamp());
		collectionDao.save(collection);
		newThingsLogic.addCollectNewThings(user, collection);
	}

	public Collection findById(Serializable id) throws SeeWorldException {
		return collectionDao.findById(id);
	}

	public boolean isAlreadyCollected(int targetId, String resourceId,
			String userId) throws SeeWorldException {
		Resource res = new Resource();
		res.setId(resourceId);
		User u = new User();
		u.setId(userId);
		List<Collection> list = collectionDao.findByThreeProperty(
				ICollectionDAO.TARGETID, targetId, ICollectionDAO.RESOURCE,
				res, ICollectionDAO.USER, u);
		return list != null && list.size() > 0;
	}
	
	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public void setMultimediaDao(IMultimediaDAO multimediaDao) {
		this.multimediaDao = multimediaDao;
	}

	public void setSingleSerialDao(ISingleSerialDAO singleSerialDao) {
		this.singleSerialDao = singleSerialDao;
	}

	public void setSerialDao(ISerialDAO serialDao) {
		this.serialDao = serialDao;
	}

	public void setVideoDao(IVideoDAO videoDao) {
		this.videoDao = videoDao;
	}

	public void setCollectionDao(ICollectionDAO collectionDao) {
		this.collectionDao = collectionDao;
	}
	public void setNewThingsLogic(INewThingsLogic newThingsLogic) {
		this.newThingsLogic = newThingsLogic;
	}

	public void setMovieDao(IMovieDAO movieDao) {
		this.movieDao = movieDao;
	}
	
	public void setiDAssistant(IDAssistant iDAssistant) {
		this.iDAssistant = iDAssistant;
	}
}
