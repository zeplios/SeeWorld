package edu.tju.ina.seeworld.po.resource;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import edu.tju.ina.seeworld.po.rbac.Resource;

/**
 * Serial entity. @author MyEclipse Persistence Tools
 */

public class Serial extends Multimedia implements java.io.Serializable {

	private static final long serialVersionUID = -3933866966925735486L;
	private Short seasons = 1;
	private Short season = 1;

	private Set<Category> category = new HashSet<Category>(0);
	private Set<SingleSerial> singleSerials = new HashSet<SingleSerial>(0);
	private Set<Director> directors = new HashSet<Director>(0);
	private Set<Actor> actors = new HashSet<Actor>(0);

	/** default constructor */
	public Serial() {
	}
	
	/** minimal constructor */
	public Serial(Set<Category> category, Resource resourceType, String title,
			String publishedYear, Timestamp addTime, Short seasons) {
		super(resourceType, title, publishedYear, addTime);
		this.category = category;
		this.seasons = seasons;
	}

	/** full constructor */
	public Serial(Set<Category> category, Set<SingleSerial> singleSerials, Resource resourceType, String title,
			String alias, String publishedYear, String image, String introduction, 
			Timestamp addTime, Boolean deleted, Boolean status, Short seasons, Short season,
			Integer clickCount, Integer commentsCount, Integer collectedCount, AreaAndCountry areaAndCountry) {
		super(resourceType, title, alias, publishedYear, image,
				introduction, addTime, clickCount, commentsCount, collectedCount,
				areaAndCountry, deleted, status);
		this.category = category;
		this.seasons = seasons;
		this.season = season;
		this.singleSerials = singleSerials;
	}

	public Short getSeasons() {
		return this.seasons;
	}

	public void setSeasons(Short seasons) {
		this.seasons = seasons;
	}

	public Set<SingleSerial> getSingleSerials() {
		return this.singleSerials;
	}

	public void setSingleSerials(Set<SingleSerial> singleSerials) {
		this.singleSerials = singleSerials;
	}

	public Short getSeason() {
		return season;
	}

	public void setSeason(Short season) {
		this.season = season;
	}

	public Set<Director> getDirectors() {
		return directors;
	}

	public void setDirectors(Set<Director> directors) {
		this.directors = directors;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

	@Override
	public String toString() {
		String title = this.getTitle();
		String season = "";
		if (this.getSeasons() != null && this.getSeasons() > 1) {
			season = "第" + this.getSeason() + "季";
		}
		return title + " " + season;
	}

}