package edu.tju.ina.seeworld.po.resource;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Format entity. @author MyEclipse Persistence Tools
 */

public class Format implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5932074612171308019L;
	private Integer id;
	private String format;
	private Timestamp addTime;
	private Set<? super Multimedia> multimedias = new HashSet<Object>(0);

	
	
	// Constructors

	/** default constructor */
	public Format() {
	}

	/** minimal constructor */
	public Format(String format, Timestamp addTime) {
		this.format = format;
		this.addTime = addTime;
	}

	/** full constructor */
	public Format(String format, Timestamp addTime, Set<? super Multimedia> multimedias) {
		this.format = format;
		this.addTime = addTime;
		this.multimedias = multimedias;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFormat() {
		return this.format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Set<? super Multimedia> getMultimedias() {
		return this.multimedias;
	}

	public void setMultimedias(Set<? super Multimedia> multimedias) {
		this.multimedias = multimedias;
	}

	public void setAddTime() {
		addTime = new Timestamp(new Date().getTime());
	}
}