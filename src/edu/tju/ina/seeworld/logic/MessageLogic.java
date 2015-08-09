package edu.tju.ina.seeworld.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import edu.tju.ina.seeworld.dao.user.IMessageDAO;
import edu.tju.ina.seeworld.dao.user.IUserDAO;
import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.po.user.Message;
import edu.tju.ina.seeworld.po.user.User;
import edu.tju.ina.seeworld.util.DateUtil;
import edu.tju.ina.seeworld.vo.MessageVO;

public class MessageLogic implements IMessageLogic {
	private IMessageDAO messagedao;
	private IUserDAO userdao;

	public void setReaded(Integer id) throws SeeWorldException {
		messagedao.updateReaded(id);
	};

	public void deleteMessage(Integer id) throws SeeWorldException {
		Message message = messagedao.findById(id);
		messagedao.delete(message);
	};

	public void setMessagedao(IMessageDAO messagedao) {
		this.messagedao = messagedao;
	}

	public void setUserdao(IUserDAO userdao) {
		this.userdao = userdao;
	}

	public Integer addMessage(String senderId, String receiverId,
			String content, Boolean reply, Boolean conceal, String location)
			throws SeeWorldException {
		Message message = new Message();
		User sender = new User();
		sender.setId(senderId);
		message.setSender(sender);
		User receiver = new User();
		receiver.setId(receiverId);
		User locationUser = userdao.findById(location);
		message.setReceiver(receiver);
		message.setRead(false);
		message.setContent(content);
		message.setReply(reply);
		message.setConcealed(conceal);
		message.setLocation(locationUser);
		message.setAddTime(DateUtil.getCurrentTimestamp());
		return (Integer) messagedao.save(message);
	};

	public JSONArray getMessageByPage(String senderId, String receiverId,
			Integer type, Integer offset, Integer pagesize)
			throws SeeWorldException {
		JSONArray messageList = new JSONArray();
		List<Message> list = messagedao.getListByPage(this.getHQL(senderId,
				receiverId, type)
				+ " order by addTime desc", offset, pagesize);
		for (Message m : list) { // VO封装
			boolean concealed = false;
			if (m.isConcealed() == true) {
				if ((m.getSender().getId().equals(senderId) || m.getReceiver()
						.getId().equals(senderId))) {
					concealed = true;
				} else {
					continue;
				}
			}
			MessageVO messagevo = new MessageVO(m);
			if (messagevo.getSender().getId().equals(senderId)) {
				messagevo.setMineMessage(true);
			} else {
				messagevo.setMineMessage(false);
			}
			messagevo.setConcealed(concealed);
			messageList.add(messagevo);
		}
		return messageList;
	}

	private String getHQL(String senderId, String receiverId, Integer type) {
		String HQL;
		if (type == IMessageDAO.TYPE_FOR_REPLY) {
			HQL = "from Message where (sender='" + senderId
					+ "' and receiver='" + receiverId + "') or (sender='"
					+ receiverId + "' and receiver='" + senderId + "')";
		} else {
			HQL = "from Message where location='" + receiverId + "'";
		}
		return HQL;
	}

	public JSONArray getNewMessage(String receiverId) throws SeeWorldException {
		List<Message> unReadedMessageList = messagedao
				.findByQuery(
						"from Message where receiver=? and readed=false order by addTime desc",
						receiverId);
		JSONArray newMessageList = new JSONArray();
		for (Message m : unReadedMessageList) {
			MessageVO messagevo = new MessageVO(m);
			newMessageList.add(messagevo);
		}
		return newMessageList;
	}

	public Integer getNewMessagesNum(String receiverId)
			throws SeeWorldException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(IMessageDAO.RECEIVER, receiverId);
		params.put(IMessageDAO.READ, false);
		return messagedao.getCount(params);
	}

}
