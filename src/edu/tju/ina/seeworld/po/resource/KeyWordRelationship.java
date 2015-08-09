package edu.tju.ina.seeworld.po.resource;

import java.sql.Timestamp;
import java.util.Date;

/**
 * KeyWordRelationShip entity. @author MyEclipse Persistence Tools
 */

public class KeyWordRelationship implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 9191523267601868649L;
	private Integer id;
	private Multimedia target;
	private KeyWord keyWord;
	private Integer weight;
	private Timestamp addTime;

	// Constructors

	/** default constructor */
	public KeyWordRelationship() {
	}

	/** full constructor */
	public KeyWordRelationship(Multimedia target, KeyWord keyWord,
			Integer weight, Timestamp addTime) {
		this.target = target;
		this.keyWord = keyWord;
		this.weight = weight;
		this.addTime = addTime;

	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Multimedia getTarget() {
		return target;
	}

	public void setTarget(Multimedia target) {
		this.target = target;
	}

	public KeyWord getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(KeyWord keyWord) {
		this.keyWord = keyWord;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Timestamp getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public void setAddTime() {
		addTime = new Timestamp(new Date().getTime());
	}
}