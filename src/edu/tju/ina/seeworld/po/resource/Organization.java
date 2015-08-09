package edu.tju.ina.seeworld.po.resource;

import java.util.HashSet;
import java.util.Set;

/**
 * Organization entity. @author Uranus
 */

public class Organization implements java.io.Serializable {

	// Fields

	private Integer id;
	private String name;
	private String introduction;
	private Set<Lecturer> lecturers = new HashSet<Lecturer>(0);
	private AreaAndCountry areaAndCountry;

	// Constructors

	/** default constructor */
	public Organization() {
	}

	/** minimal constructor */
	public Organization(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public Organization(String name, String introduction,
			Set<Lecturer> lecturers,AreaAndCountry anc) {
		this.name = name;
		this.introduction = introduction;
		this.lecturers = lecturers;
		this.areaAndCountry = anc;
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

	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public AreaAndCountry getAreaAndCountry() {
		return areaAndCountry;
	}

	public void setAreaAndCountry(AreaAndCountry areaAndCountry) {
		this.areaAndCountry = areaAndCountry;
	}

	public Set<Lecturer> getLecturers() {
		return this.lecturers;
	}

	public void setLecturers(Set<Lecturer> lecturers) {
		this.lecturers = lecturers;
	}
	private static final long serialVersionUID = -384330763611715127L;
}