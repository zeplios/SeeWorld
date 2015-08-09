package edu.tju.ina.seeworld.po.rbac;

import java.sql.Timestamp;

/**
 * Setting entity. @author MyEclipse Persistence Tools
 */

public class Setting implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4955594943946135152L;
	private Integer id;
	private String name;
	private String value;
	private Timestamp addTime;
	// Constructors

	/** default constructor */
	public Setting() {
	}

	/** full constructor */
	public Setting(String name, String value) {
		this.name = name;
		this.value = value;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

}