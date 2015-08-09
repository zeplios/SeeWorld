package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;

/**
 * NewThingsReply entity. @author MyEclipse Persistence Tools
 */

public class NewThingsReply implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 4203072546618453030L;
	private Integer id;
	private User userByReceiver;
	private User userBySender;
	private NewThings newThings;
	private Boolean reply;
	private Boolean readed;
	private String content;
	private Timestamp addTime;
	private User hostUser;

	// Constructors

	/** default constructor */
	public NewThingsReply() {
	}

	/** minimal constructor */
	public NewThingsReply(User userByReceiver, User userBySender,
			NewThings newThings, Boolean reply, String content, Timestamp addTime) {
		this.userByReceiver = userByReceiver;
		this.userBySender = userBySender;
		this.newThings = newThings;
		this.reply = reply;
		this.content = content;
		this.addTime = addTime;
	}



	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserByReceiver() {
		return this.userByReceiver;
	}

	public void setUserByReceiver(User userByReceiver) {
		this.userByReceiver = userByReceiver;
	}

	public User getUserBySender() {
		return this.userBySender;
	}

	public void setUserBySender(User userBySender) {
		this.userBySender = userBySender;
	}

	public NewThings getNewThings() {
		return this.newThings;
	}

	public void setNewThings(NewThings newThings) {
		this.newThings = newThings;
	}

	public Boolean getReply() {
		return reply;
	}

	public void setReply(Boolean reply) {
		this.reply = reply;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Boolean getReaded() {
		return readed;
	}

	public void setReaded(Boolean readed) {
		this.readed = readed;
	}

	public User getHostUser() {
		return hostUser;
	}

	public void setHostUser(User hostUser) {
		this.hostUser = hostUser;
	}

	
}