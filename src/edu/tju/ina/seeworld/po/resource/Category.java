package edu.tju.ina.seeworld.po.resource;

// default package

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 8811201567795099440L;
	private Integer id;
	private Category parent;
	private String name;
	private Timestamp addTime;
	private Set<Category> categories = new HashSet<Category>(0);
	private Set<? extends Multimedia> multimedias = new HashSet<Multimedia>(0);

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** minimal constructor */
	public Category(String name, Timestamp addTime) {
		this.name = name;
		this.addTime = addTime;
	}

	/** minimal constructor */
	public Category(Category category, String name,	Timestamp addTime) {
		this.parent = category;
		this.name = name;
		this.addTime = addTime;
	}

	/** full constructor */
	public Category(Category category, String name, 
			Timestamp addTime, Set<Category> categories,
			Set<? extends Multimedia> multimedias) {
		this.parent = category;
		this.name = name;
		this.addTime = addTime;
		this.categories = categories;
		this.multimedias = multimedias;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Category getParent() {
		return this.parent;
	}

	public void setParent(Category category) {
		this.parent = category;
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

	public Set<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Set<? extends Multimedia> getMultimedias() {
		return this.multimedias;
	}

	public void setMultimedias(Set<? extends Multimedia> multimedias) {
		this.multimedias = multimedias;
	}

	public boolean equals(Object aCategory) {
		if (aCategory instanceof Category) {
			return this.id == ((Category) aCategory).getId();
		} else {
			return false;
		}
	}
}