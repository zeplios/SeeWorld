package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;

/**
 * PrivacySetting entity. @author Uranus
 */

public class PrivacySetting implements java.io.Serializable {

	// Fields
	private Integer id;
	private Integer typeId;
	private User user;
	private Boolean concealed = false;
	private Timestamp addTime;

	// Constructors

	/** default constructor */
	public PrivacySetting() {
	}

	/** full constructor */
	public PrivacySetting(Integer typeID, User user, Boolean concealed) {
		this.typeId = typeID;
		this.user = user;
		this.concealed = concealed;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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

	private static final long serialVersionUID = 6699378907715339710L;
}