package edu.tju.ina.seeworld.vo;

import java.sql.Timestamp;
import java.util.Date;

public class NewThingsVO {
	private Integer id;
	private SimpleUserVO user;
	private SimpleMultimedia target;
	
	/* 新鲜事的类型，对应评论，收藏和观看 */
	private Integer typeId;
	/* 新鲜事类型，由typeId得到的中文描述 */
	private String operation = "";
	/* 附加信息，观看和收藏的时候内容为收看对象的简介；评论时内容为评论的内容 */
	private String otherInfo;
	
//	private MessageVO firstReply;
//	private MessageVO lastReply;
	private Integer replyNum;
	private String modifiedTime;

	public NewThingsVO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

//	public void setFirstReply(MessageVO firstReply) {
//		this.firstReply = firstReply;
//	}
//
//	public void setLastReply(MessageVO lastReply) {
//		this.lastReply = lastReply;
//	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public SimpleUserVO getUser() {
		return user;
	}

	public void setUser(SimpleUserVO user) {
		this.user = user;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp time) {
		long now = new Date().getTime();
		long minute = now / 1000 / 60 - time.getTime() / 1000 / 60;
		if(minute==0){
			modifiedTime = "刚刚";
		}
		else if (minute < 60) {
			modifiedTime = minute + "分钟前";
		} else if (minute < 60 * 24 && minute >= 60) {
			modifiedTime = minute / 60 + "小时前";
		} else if (minute < 60 * 24 * 5 && minute >= 60 * 24) {
			modifiedTime = minute / 60 / 24 + "天前";
		} else {
			modifiedTime = "n天前";
		}

	}

	public String getOperation() {
		if(operation == null)
			operation = "";
		return operation;
	}

	/**
	 * 与typeID属性关联，诸如“评论了”、“收藏了”、“观看了”等
	 */
	public void setOperation(String operation) {
		if(operation != null)
			this.operation = operation + "了";
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

//	public MessageVO getFirstReply() {
//		return firstReply;
//	}
//
//	public void setFirstReply(NewThingsReply firstReply, String MyID) {
//		if (firstReply != null) {
//			this.firstReply = new MessageVO(firstReply, id);
//			if (this.firstReply.getSender().getId().equals(MyID)) {
//				this.firstReply.setMineMessage(true);
//			} else {
//				this.firstReply.setMineMessage(false);
//			}
//		}
//	}
//
//	public MessageVO getLastReply() {
//		return lastReply;
//	}
//
//	public void setLastReply(NewThingsReply lastReply, String MyID) {
//		if (lastReply != null) {
//			this.lastReply = new MessageVO(lastReply, id);
//			if (this.lastReply.getSender().getId().equals(MyID)) {
//				this.lastReply.setMineMessage(true);
//			} else {
//				this.lastReply.setMineMessage(false);
//			}
//		}
//	}

	public SimpleMultimedia getTarget() {
		return target;
	}

	public void setTarget(SimpleMultimedia target) {
		this.target = target;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

	@Override
	public String toString() {
		return "用户" + user.getUserName() + getOperation()
				+ target.getTitle();
	}
}
