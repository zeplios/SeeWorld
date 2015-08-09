package edu.tju.ina.seeworld.logic;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.tju.ina.seeworld.dao.user.INewThingsDAO;
import edu.tju.ina.seeworld.dao.user.INewThingsReplyDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.dao.user.NewThingsDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.rbac.Resource;
import edu.tju.ina.seeworld.po.user.Collection;
import edu.tju.ina.seeworld.po.user.Comment;
import edu.tju.ina.seeworld.po.user.NewThings;
import edu.tju.ina.seeworld.po.user.NewThingsReply;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.util.DateUtil;
import edu.tju.ina.seeworld.util.VOPOTransformator;
import edu.tju.ina.seeworld.vo.MessageVO;
import edu.tju.ina.seeworld.vo.NewThingsVO;

/**
 * 主要支持的操作有：
 * USER_OPERATION_COLLECT = 2;
 * USER_OPERATION_COMMENT = 3;
 * USER_OPERATION_VIEW = 4;
 * USER_OPERATION_UPLOAD = 5;还未加上
 * USER_OPERATION_RECOMMEND = 7;还未加上
 * @author zhfch
 */
public class NewThingsLogic implements INewThingsLogic {
	private INewThingsDAO newThingsDao;
	private IUserDAO userDao;
	private INewThingsReplyDAO newThingsReplyDao;
	private VOPOTransformator vOPOTransformator;

	public List<NewThingsVO> moreNewThings(int start, int resultSize, User user)
			throws SeeWorldException {
		// TODO 只能查看好友的新鲜事
		// List<Friend> friendList = friendDao.findByProperty(IFriendDAO.OFFER,
		// user);
		// List<String> idList = new ArrayList<String>();
		// for (Friend f : friendList) {
		// idList.add(f.getUserTheOther().getId());
		// }
		// List<NewThings> newThingsList = newThingsDao.findByList(idList,
		// start,
		// resultSize);
		String id = user.getId();
		String hql = "from NewThings n where n.user.id !='" + id + "' order by addTime desc";
		List<NewThings> newThingsList = newThingsDao.getListByPage(hql, start, resultSize);
		List<NewThingsVO> newThingsVoList = new ArrayList<NewThingsVO>();
		for (NewThings nt : newThingsList) {
			try{
				NewThingsVO newthingsvo = vOPOTransformator.transferNewThingsToVO(
						nt, user.getId());
				newThingsVoList.add(newthingsvo);
			} catch(Exception e){
				continue;
			}
		}
		return newThingsVoList;
	}

	public void addCollectNewThings(User user, Collection collection)
			throws SeeWorldException {
		NewThings newthings = new NewThings();
		newthings.setUser(user);
		newthings.setTargetId(collection.getId());
		newthings.setConcealed(false);
		newthings.setAddTime(DateUtil.getCurrentTimestamp());
		newthings.setTypeId(Constant.USER_OPERATION_COLLECT);
		newthings.setFirstReply(null);
		newthings.setLastReply(null);
		newthings.setReplyNum(0);
		newthings.setResource(collection.getResource());
		newThingsDao.save(newthings);
	}

	public void addCommentNewThings(User user, Comment comment)
			throws SeeWorldException {
		NewThings newthings = new NewThings();
		newthings.setUser(user);
		newthings.setTargetId(comment.getId());
		newthings.setConcealed(false);
		newthings.setAddTime(new Timestamp(new Date().getTime()));
		newthings.setTypeId(Constant.USER_OPERATION_COMMENT);
		newthings.setFirstReply(null);
		newthings.setLastReply(null);
		newthings.setReplyNum(0);
		newthings.setResource(comment.getResource());
		newThingsDao.save(newthings);
	}

	// TODO 添加收看记录
	public void addViewNewThings(User user, Integer targetId, Resource resource)
			throws SeeWorldException {
		NewThings newthings ;
		List<NewThings> list = newThingsDao.findByThreeProperty(
				NewThingsDAO.RESOURCE, resource, NewThingsDAO.USER, user, NewThingsDAO.TARGETID, targetId);
		if(list != null && list.size() > 0){
			newthings = list.get(0);
			newthings.setAddTime(new Timestamp(new Date().getTime()));
			newThingsDao.update(newthings);
		}else{
			newthings= new NewThings();
			newthings.setUser(user);
			newthings.setResource(resource);
			newthings.setTypeId(Constant.USER_OPERATION_VIEW);
			newthings.setTargetId(targetId);
			newthings.setConcealed(false);
			newthings.setAddTime(new Timestamp(new Date().getTime()));
			newthings.setFirstReply(null);
			newthings.setLastReply(null);
			newthings.setReplyNum(0);
			newThingsDao.save(newthings);
		}
	}

	public Integer addNewThingsReply(Integer newThingsID, Boolean reply,
			User me, String toUserID, String content) throws SeeWorldException {
		NewThings newThings = newThingsDao.findById(newThingsID);
		User receiver = newThings.getUser();
		if (reply) {
			receiver = userDao.findById(toUserID);
		}
		NewThingsReply newThingsReply = new NewThingsReply();
		newThingsReply.setContent(content);
		newThingsReply.setNewThings(newThings);
		if (newThings.getUser().getId().equals(me.getId())) {
			newThingsReply.setReaded(true);
		} else {
			newThingsReply.setReaded(false);
		}
		newThingsReply.setReply(reply);
		newThingsReply.setUserByReceiver(receiver);
		newThingsReply.setUserBySender(me);
		newThingsReply.setAddTime(new Timestamp(new Date().getTime()));
		newThingsReply.setHostUser(newThings.getUser());
		Integer id = (Integer) newThingsReplyDao.save(newThingsReply);
		newThingsReply.setId(id);
		Integer replynum = newThings.getReplyNum();
		if (replynum == 0) {
			newThings.setFirstReply(newThingsReply);
		} else {
			newThings.setLastReply(newThingsReply);
		}
		replynum++;
		newThings.setReplyNum(replynum);
		newThingsDao.update(newThings);
		return id;
	}

	public Integer deleteNewThingsReply(Integer ID) throws SeeWorldException {
		NewThingsReply newThingsReply = newThingsReplyDao.findById(ID);
		NewThings newthings = newThingsReply.getNewThings();
		Boolean update = false;
		if (newthings.getReplyNum() > 0) {
			if (newthings.getFirstReply().getId() == ID)
				update = true;
			if (newthings.getReplyNum() > 1
					&& newthings.getLastReply().getId() == ID)
				update = true;
		}
		newThingsReplyDao.delete(newThingsReply);
		Integer replyNum = newthings.getReplyNum();
		replyNum--;
		if (update) {
			newthings.setFirstReply(null);
			newthings.setLastReply(null);
			List<NewThingsReply> list = newThingsReplyDao.findByProperty(
					"newThings", newthings);
			int listsize = list.size();
			if (listsize > 0)
				newthings.setFirstReply(list.get(0));
			if (listsize > 1)
				newthings.setLastReply(list.get(listsize - 1));
		}
		newthings.setReplyNum(replyNum);
		newThingsDao.update(newthings);
		return newthings.getId();
	}

	public List<MessageVO> viewAllNewThingsReply(Integer ID, User me)
			throws SeeWorldException {
		List<MessageVO> allNewThingsReplyList = new ArrayList<MessageVO>();
		NewThings newthings = newThingsDao.findById(ID);
		List<NewThingsReply> list = newThingsReplyDao.findByProperty(
				"newThings", newthings);
		for (NewThingsReply reply : list) {
			MessageVO message = new MessageVO(reply, newthings.getId());
			if (me.getId().equals(message.getSender().getId())) {
				message.setMineMessage(true);
			} else {
				message.setMineMessage(false);
			}
			allNewThingsReplyList.add(message);
		}
		return allNewThingsReplyList;
	}

	public List<MessageVO> viewNewReply(User me) throws SeeWorldException {
		List<MessageVO> NewReplyList = new ArrayList<MessageVO>();
		Iterator<NewThingsReply> list = newThingsReplyDao.findByQuery(
				"from NewThingsReply where ( receiver='" + me.getId()
						+ "' or hostUser='" + me.getId()
						+ "' ) and readed=0 order by newThings desc")
				.iterator();
		while (list.hasNext()) {
			NewThingsReply reply = list.next();
			MessageVO message = new MessageVO(reply, reply.getNewThings()
					.getId());
			NewReplyList.add(message);
		}
		return NewReplyList;
	}

	/**
	 * 返回用户user的某一类型新鲜事，typeId为0时，表示所有新鲜事
	 * @param start
	 * @param resultSize
	 * @param user
	 * @param typeId
	 * @return
	 * @throws SeeWorldException
	 */
	public List<NewThingsVO> viewNewThingsOfUser(int start, int resultSize,
			User user, int typeId) throws SeeWorldException {
		String id = user.getId();
		String hql = null;
		if (typeId == 0) {
			hql = "from NewThings n where n.user.id ='" + id
					+ "' order by addTime desc";
		} else {
			hql = "from NewThings n where n.user.id ='" + id
					+ "' and n.typeId=" + typeId + " order by addTime desc";
		}
		List<NewThings> newThingsList = newThingsDao.getListByPage(hql, start,
				resultSize);
		List<NewThingsVO> newThingsVoList = new ArrayList<NewThingsVO>();
		for (NewThings nt : newThingsList) {
			try{
				NewThingsVO newthingsvo = vOPOTransformator.transferNewThingsToVO(
						nt, user.getId());
				newThingsVoList.add(newthingsvo);
			} catch(Exception e){
				//如果视频被删，会抛出空指针异常
				continue;
			}
		}
		return newThingsVoList;
	}

	public void setReplyReaded(Integer ID, User me) throws SeeWorldException {
		newThingsReplyDao.updateReaded(ID, me.getId());
	}

	public void deleteNewThings(int ID) throws SeeWorldException {
		NewThings newthings = newThingsDao.findById(ID);
		newThingsDao.delete(newthings);
	}

	public NewThingsVO getOneNewThing(int ID, User user)
			throws SeeWorldException {
		NewThings newthing = newThingsDao.findById(ID);
		NewThingsVO newthingsvo = vOPOTransformator.transferNewThingsToVO(
				newthing, user.getId());
		return newthingsvo;
	}

	public void setNewThingsDao(INewThingsDAO newThingsDao) {
		this.newThingsDao = newThingsDao;
	}

	public void setUserDao(IUserDAO userDao) {
		this.userDao = userDao;
	}

	public void setvOPOTransformator(VOPOTransformator vOPOTransformator) {
		this.vOPOTransformator = vOPOTransformator;
	}

	public void setNewThingsReplyDao(INewThingsReplyDAO newThingsReplyDao) {
		this.newThingsReplyDao = newThingsReplyDao;
	}

}
