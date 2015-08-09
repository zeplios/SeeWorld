package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;

import edu.tju.ina.seeworld.po.rbac.Resource;

/**
 * Comment entity. @author MyEclipse Persistence Tools
 */

public class Comment implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private Integer targetID;
	private String title;
	private String content;
	private Timestamp addTime;
	private Comment replyTo;
	private Integer support;
	private Integer object;
	private Resource resource;
	// Constructors

	/** default constructor */
	public Comment() {
	}

	/** minimal constructor */
	public Comment(User user, Integer targetID, String title, String content) {
		this.user = user;
		this.targetID = targetID;
		this.title = title;
		this.content = content;
	}

	/** full constructor */
	public Comment(User user, Integer targetID, String title, String content,
			Timestamp addTime) {
		this.user = user;
		this.targetID = targetID;
		this.title = title;
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

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Integer getTargetID() {
		return targetID;
	}

	public void setTargetID(Integer targetID) {
		this.targetID = targetID;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Comment getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(Comment replyTo) {
		this.replyTo = replyTo;
	}

	public Integer getSupport() {
		return support;
	}

	public void setSupport(Integer support) {
		this.support = support;
	}

	public Integer getObject() {
		return object;
	}

	public void setObject(Integer object) {
		this.object = object;
	}

	private static final long serialVersionUID = 664981464247519602L;
}