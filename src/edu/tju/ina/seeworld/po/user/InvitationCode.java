package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;
import java.util.Date;

/**
 * InvitationCode entity. @author MyEclipse Persistence Tools
 */

public class InvitationCode implements java.io.Serializable {

	// Fields
	private String id;
	private User user;
	private Timestamp addTime;
	private Timestamp expiredTime;
	private Boolean used = true;

	// Constructors

	/** default constructor */
	public InvitationCode() {
	}

	/** full constructor */
	public InvitationCode(User user, Timestamp expiredTime, Boolean used) {
		this.user = user;
		this.expiredTime = expiredTime;
		this.used = used;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	/**
	 * 在存入数据库之前必须显式地调用,以邀请码的内容作为其PK.
	 * 
	 * @param id
	 *            长度为十位,由字母,数字构成
	 */
	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Timestamp getExpiredTime() {
		return this.expiredTime;
	}

	public void setExpiredTime(Timestamp expiredTime) {
		this.expiredTime = expiredTime;
	}

	/**
	 * 验证证是否过期
	 * 
	 * @return
	 */
	public Boolean isExpired() {
		return this.expiredTime.before(new Date());
	}

	public Boolean isUsed() {
		return this.used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	private static final long serialVersionUID = 1562029854943245273L;
}