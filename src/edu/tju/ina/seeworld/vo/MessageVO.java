package edu.tju.ina.seeworld.vo;

import edu.tju.ina.seeworld.po.user.Message;
import edu.tju.ina.seeworld.po.user.NewThingsReply;
import edu.tju.ina.seeworld.util.DateUtil;

public class MessageVO {
	private Integer id;
	private SimpleUserVO sender;
	private String content;
	private Boolean read = false;
	private Boolean concealed;
	private Boolean reply;
	private Boolean mineMessage;
	private String addTime;
	private Integer reference;

	public MessageVO(Message message) {
		this.setContent(message.getContent());
		this.setId(message.getId());
		this.setRead(message.isRead());
		this.setReply(message.isReply());
		this.setSender(new SimpleUserVO(message.getSender()));
		this.setAddTime(DateUtil.getFormattedDateString(message.getAddTime()));
	}

	public MessageVO(NewThingsReply message, Integer newThingsID) {
		this.setContent(message.getContent());
		this.setId(message.getId());
		this.setRead(message.getReaded());
		this.setReply(message.getReply());
		this.setSender(new SimpleUserVO(message.getUserBySender()));
		this.setAddTime(message.getAddTime().toString());
		this.reference = newThingsID;
	}

	public MessageVO() {
		sender = new SimpleUserVO();
	}

	public Boolean getReply() {
		return reply;
	}

	public Boolean getMineMessage() {
		return mineMessage;
	}

	public void setMineMessage(Boolean mineMessage) {
		this.mineMessage = mineMessage;
	}

	public void setReply(Boolean reply) {
		this.reply = reply;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SimpleUserVO getSender() {
		return sender;
	}

	public void setSender(SimpleUserVO sender) {
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Boolean getConcealed() {
		return concealed;
	}

	public void setConcealed(Boolean concealed) {
		this.concealed = concealed;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

}
