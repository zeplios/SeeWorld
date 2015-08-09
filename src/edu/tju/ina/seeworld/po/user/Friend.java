package edu.tju.ina.seeworld.po.user;

import java.sql.Timestamp;

/**
 * Friend entity. @author MyEclipse Persistence Tools
 */

public class Friend implements java.io.Serializable {

	// Fields

	private Integer id;
	/**
	 * 代表好友关系中的别一个方
	 */
	private User userTheOther;
	/**
	 * 主动方,代表好友关系中的"我"
	 */
	private User userOffer;
	private Timestamp addTime;

	// Constructors

	/** default constructor */
	public Friend() {
	}

	/**the only constructor */
	public Friend(User userTheOther, User userOffer) throws RuntimeException{
		this.userTheOther = userTheOther;
		this.userOffer = userOffer;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUserTheOther() {
		return this.userTheOther;
	}

	public void setUserTheOther(User userTheOther) {
		this.userTheOther = userTheOther;
	}

	public User getUserOffer() {
		return this.userOffer;
	}

	public void setUserOffer(User userOffer) {
		this.userOffer = userOffer;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	@Override
	public boolean equals(Object instance){
		if(instance instanceof Friend){
			if((this.userOffer==((Friend)instance).userOffer&&this.userTheOther==((Friend)instance).userTheOther)||(this.userOffer == ((Friend)instance).userTheOther&&this.userTheOther ==((Friend)instance).userOffer)){
				return true;
			}
		}
		return false;
	}

	private static final long serialVersionUID = 8150238422279032769L;
}