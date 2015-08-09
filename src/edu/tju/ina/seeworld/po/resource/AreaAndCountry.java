package edu.tju.ina.seeworld.po.resource;

import java.sql.Timestamp;
import java.util.Set;

/**
 * AreaAndCountry entity. @author MyEclipse Persistence Tools
 */

public class AreaAndCountry implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 8260897883502906203L;
	private Integer id;
	private String name;
	private String alias;
	private Timestamp addTime;
	private Set<? extends Multimedia> multimedias;

	// Constructors

	/** default constructor */
	public AreaAndCountry() {
	}
	
	public AreaAndCountry(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AreaAndCountry(String name, String alias, 
			Timestamp addTime) {
		this.name = name;
		this.alias = alias;
		this.addTime = addTime;
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

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Set<? extends Multimedia> getMultimedias() {
		return multimedias;
	}

	public void setMultimedias(Set<? extends Multimedia> multimedias) {
		this.multimedias = multimedias;
	}
}