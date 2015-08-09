package edu.tju.ina.seeworld.po.resource;

import java.sql.Timestamp;

/**
 * ShowInMainpage entity. @author MyEclipse Persistence Tools
 */

public class Mainpage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8109747973740751718L;
	private Integer id;
	private Multimedia multimedia;
	private String briefDescription;
	private String image;
	private Boolean isScroll;
	private Boolean isStatic;
	private Timestamp addTime;

	// Constructors

	/** default constructor */
	public Mainpage() {
	}

	/** minimal constructor */
	public Mainpage(Timestamp addTime) {
		this.addTime = addTime;
	}

	/** full constructor */
	public Mainpage(Multimedia multimedia, String briefDescription,
			String image, Boolean isScroll, Boolean isStatic, Timestamp addTime) {
		this.multimedia = multimedia;
		this.briefDescription = briefDescription;
		this.image = image;
		this.isScroll = isScroll;
		this.isStatic = isStatic;
		this.addTime = addTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Multimedia getMultimedia() {
		return this.multimedia;
	}

	public void setMultimedia(Multimedia multimedia) {
		this.multimedia = multimedia;
	}

	public String getBriefDescription() {
		return this.briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getIsScroll() {
		return this.isScroll;
	}

	public void setIsScroll(Boolean isScroll) {
		this.isScroll = isScroll;
	}

	public Boolean getIsStatic() {
		return this.isStatic;
	}

	public void setIsStatic(Boolean isStatic) {
		this.isStatic = isStatic;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

}