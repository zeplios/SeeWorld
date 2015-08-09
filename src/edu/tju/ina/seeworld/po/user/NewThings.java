package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;

import edu.tju.ina.seeworld.po.rbac.Resource;

/**
 * NewThings entity. @author MyEclipse Persistence Tools
 */

public class NewThings implements java.io.Serializable {

	private static final long serialVersionUID = 319058309685530341L;
	private Integer id;
	private User user;
	private NewThingsReply lastReply;
	private NewThingsReply firstReply;
	/**
	 * typeId=COMMENT时，targetId指向comment
	 * typeId=COLLECT时，targetId指向collect
	 * typeId=VIEW时，targetId指向multimedia
	 * typeId=RECOMMEND时，targetId指向recommendation
	 * typeId=MESSAGE时，targetId指向message
	 */
	private Integer targetId;
	private Boolean concealed;
	private Timestamp addTime;
	private Integer replyNum;
	private Integer typeId;
	private Resource resource;

	/** default constructor */
	public NewThings() {
	}

	/** minimal constructor */
	public NewThings(User user, Integer targetId, Boolean concealed,
			Timestamp addTime, Integer typeId) {
		this.user = user;
		this.targetId = targetId;
		this.concealed = concealed;
		this.addTime = addTime;
		this.typeId = typeId;
	}

	/** full constructor */
	public NewThings(NewThingsReply lastReply, User user,
			NewThingsReply firstReply, Integer targetId,
			Boolean concealed, Timestamp addTime, Integer typeId,
			Integer replyNum) {
		this.lastReply = lastReply;
		this.user = user;
		this.firstReply = firstReply;
		this.targetId = targetId;
		this.concealed = concealed;
		this.addTime = addTime;
		this.typeId = typeId;
		this.replyNum = replyNum;

	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public NewThingsReply getFirstReply() {
		return this.firstReply;
	}

	public void setFirstReply(NewThingsReply firstReply) {
		this.firstReply = firstReply;
	}

	public NewThingsReply getLastReply() {
		return this.lastReply;
	}

	public void setLastReply(NewThingsReply lastReply) {
		this.lastReply = lastReply;
	}

	public Boolean getConcealed() {
		return this.concealed;
	}

	public void setConcealed(Boolean concealed) {
		this.concealed = concealed;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getReplyNum() {
		return this.replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}
}