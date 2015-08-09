package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;

/**
 * Status entity. @author MyEclipse Persistence Tools
 */

public class Status implements java.io.Serializable {

	// Fields

	private Integer id;
	private User user;
	private String status;
	private Timestamp addTime;

	// Constructors

	/** default constructor */
	public Status() {
	}

	/** minimal constructor */
	public Status(User user) {
		this.user = user;
	}

	/** full constructor */
	public Status(User user, String status) {
		this.user = user;
		this.status = status;
	}

	// Property accessors

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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	private static final long serialVersionUID = -1788912512411576812L;
}