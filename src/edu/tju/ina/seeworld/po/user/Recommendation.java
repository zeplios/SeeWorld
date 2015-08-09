package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;

import edu.tju.ina.seeworld.po.rbac.Resource;

/**
 * Recommendation entity. @author Uranus
 */

public class Recommendation implements java.io.Serializable,Comparable<Recommendation> {

	// Fields

	private Integer id;
	private User receiver;
	private User sender;
	private Integer targetID;
	private Timestamp addTime;
	private String message;
	private Boolean isRead = false;
	private Resource resource;

	// Constructors

	/** default constructor */
	public Recommendation() {
	}

	/** minimal constructor */
	public Recommendation(User receiver, User sender,
			Integer targetID) {
		this.receiver = receiver;
		this.sender = sender;
		this.targetID = targetID;
	}

	/** full constructor */
	public Recommendation(User receiver, User sender,
			Integer targetID, String message) {
		this.receiver = receiver;
		this.sender = sender;
		this.targetID = targetID;
		this.message = message;
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
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


	public Integer getTargetID() {
		return targetID;
	}

	public void setTargetID(Integer targetID) {
		this.targetID = targetID;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private static final long serialVersionUID = -981297288563215780L;

	public int compareTo(Recommendation o) {
		return this.addTime.compareTo(o.getAddTime());
	}
}