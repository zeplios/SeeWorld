package edu.tju.ina.seeworld.struts.action.user;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.json.annotations.JSON;

import com.opensymphony.xwork2.Action;

import edu.tju.ina.seeworld.exception.SeeWorldException;
import edu.tju.ina.seeworld.logic.IMessageLogic;
import edu.tju.ina.seeworld.logic.SettingLogic;
import edu.tju.ina.seeworld.struts.action.common.AjaxAction;
import edu.tju.ina.seeworld.util.Constant;
import edu.tju.ina.seeworld.vo.Pagination;

public class MessageAjaxAction extends AjaxAction {
	private static final long serialVersionUID = 1628406884181322133L;
	private IMessageLogic messageLogic;
	private String receiverId;
	private String content;
	private int id;
	private Integer type;
	private Integer newMessagesNum;
	private SettingLogic settingLogic;
	private JSONArray messageList;
	private JSONArray newMessageList;
	private boolean reply;
	private boolean conceal;
	private String location;

	public void setConceal(boolean conceal) {
		this.conceal = conceal;
	}

	public String addMessage() {
		try {
			String senderId = getCurrentUser().getId();
			id = messageLogic.addMessage(senderId, receiverId, content, reply,
					conceal, location);
		} catch (SeeWorldException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String findMessageByPage() {
		page = getPage();
		page.setLen(getNewMessagesNum());
		page.setPagelist();
		int offset = page.getStart();
		int pageSize = page.getPagesize();
		try {
			String currentUserId = getCurrentUser().getId();
			messageList = messageLogic.getMessageByPage(receiverId,
					currentUserId, type, offset, pageSize);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pageJSON = JSONObject.fromObject(page);
		return SUCCESS;
	}

	@Override
	@JSON(serialize = false)
	public Pagination getPage() {
		Pagination page = new Pagination();
		try {
			page.setPagesize(settingLogic
					.getIntConfigValue(Constant.MESSAGES_PER_PAGE));
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		page.setCurrentpage(getCurrentPage());
		return page;
	}

	public Integer getNewMessagesNum() {
		if (newMessagesNum == null) {
			try {
				String currentId = getCurrentUser().getId();
				newMessagesNum = messageLogic.getNewMessagesNum(currentId);
			} catch (SeeWorldException e) {
				e.printStackTrace();
			}
		}
		return newMessagesNum;
	}

	public void setNewMessagesNum(Integer newMessagesNum) {
		this.newMessagesNum = newMessagesNum;
	}

	public String deleteMessage() {
		try {
			messageLogic.deleteMessage(id);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setReply(boolean reply) {
		this.reply = reply;
	}

	public String setReaded() {
		try {
			messageLogic.setReaded(id);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	public JSONArray getNewMessageList() {
		return newMessageList;
	}

	public String searchNewMessages() {
		try {
			String currentId = getCurrentUser().getId();
			newMessageList = messageLogic.getNewMessage(currentId);
		} catch (SeeWorldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public JSONArray getMessageList() {
		return messageList;
	}

	public void setMessageLogic(IMessageLogic messageLogic) {
		this.messageLogic = messageLogic;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSettingLogic(SettingLogic settingLogic) {
		this.settingLogic = settingLogic;
	}
	
	
}
