package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Specialty entity. @author MyEclipse Persistence Tools
 */

public class Specialty implements java.io.Serializable {

	// Fields

	private Integer id;
	private Academy academy;
	private String name;
	private Timestamp addTime;
	private Set<User> users = new HashSet<User>(0);

	// Constructors

	/** default constructor */
	public Specialty() {
	}

	/** minimal constructor */
	public Specialty(Academy academy, String name) {
		this.academy = academy;
		this.name = name;
	}

	/** full constructor */
	public Specialty(Academy academy, String name, Set<User> users) {
		this.academy = academy;
		this.name = name;
		this.users = users;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Academy getAcademy() {
		return this.academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	private static final long serialVersionUID = -3428942095790714226L;
}