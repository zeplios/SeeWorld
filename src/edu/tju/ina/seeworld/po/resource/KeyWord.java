package edu.tju.ina.seeworld.po.resource;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * KeyWord entity. @author uranus
 */

public class KeyWord implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 2620180629954385871L;
	private Integer id;
	private String keyword;
	private Timestamp addTime;
	private Set<KeyWordRelationship> keyWordRelationships = new HashSet<KeyWordRelationship>(0);
	
	// Constructors

	/** default constructor */
	public KeyWord() {
	}

	/** minimal constructor */
	public KeyWord(String keyword, Timestamp addTime) {
		this.keyword = keyword;
		this.addTime = addTime;
	}

	/** full constructor */
	public KeyWord(String keyword, Timestamp addTime, Set<KeyWordRelationship> keyWordRelationShips) {
		this.keyword = keyword;
		this.addTime = addTime;
		this.keyWordRelationships = keyWordRelationShips;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public Set<KeyWordRelationship> getKeyWordRelationships() {
		return this.keyWordRelationships;
	}

	public void setKeyWordRelationships(Set<KeyWordRelationship> keyWordRelationShips) {
		this.keyWordRelationships = keyWordRelationShips;
	}

	public void setAddTime() {
		addTime = new Timestamp(new Date().getTime());
	}
}