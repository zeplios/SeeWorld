package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message implements java.io.Serializable {

	// Fields

	private Integer id;
	private User receiver;
	private User sender;
	private String content;
	private Boolean read = false;
	private Boolean concealed;
	private Timestamp addTime;
	private Boolean reply = false;
	private User location;
	// Constructors

	public void setReply(Boolean reply) {
		this.reply = reply;
	}
	public Boolean isReply() {
		return this.reply;
	}

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(User receiver, User sender, String content,Boolean concealed,User location) {
		this.receiver = receiver;
		this.sender = sender;
		this.content = content;
		this.read = false;
		this.concealed = concealed;
		this.location=location;
	}

	/** full constructor */
	public Message(User receiver, User sender, String content,Boolean concealed, Timestamp addTime,User location) {
		this.receiver = receiver;
		this.sender = sender;
		this.content = content;
		this.read = false;
		this.concealed = concealed;
		this.addTime = addTime;
		this.location=location;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getReceiver() {
		return this.receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public User getSender() {
		return this.sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean isRead() {
		return this.read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public Boolean isConcealed() {
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

	public User getLocation() {
		return location;
	}
	public void setLocation(User location) {
		this.location = location;
	}

	private static final long serialVersionUID = -5277769640734776344L;
}