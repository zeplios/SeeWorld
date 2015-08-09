package edu.tju.ina.seeworld.po.resource;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import edu.tju.ina.seeworld.po.rbac.Resource;

/**
 * Movie entity. @author Uranus
 */

public class Movie extends Multimedia implements java.io.Serializable {

	private String path;
	private Integer size;
	/**
	 * 电影上传进度
	 */
	private Float process;

	private Set<Category> category = new HashSet<Category>(0);
	private Set<Director> directors = new HashSet<Director>(0);
	private Set<Actor> actors = new HashSet<Actor>(0);

	// Constructors

	/** default constructor */
	public Movie() {
	}

	/** minimal constructor */
	public Movie(Set<Category> category, Resource resourceType, String title,
			String publishedYear, String path, Integer size, Float process,
			Timestamp addTime) {
		super(resourceType, title, publishedYear, addTime);
		this.category = category;
		this.path = path;
		this.size = size;
		this.process = process;
	}

	/** full constructor */
	public Movie(Set<Category> category, Resource resourceType, String title,
			String alias, String publishedYear, String image, String path,
			String introduction, Integer size, Float process,
			Timestamp addTime, Boolean deleted, Boolean status,
			Integer clickCount, Integer commentsCount, Integer collectedCount,
			 AreaAndCountry areaAndCountry) {
		super(resourceType, title, alias, publishedYear, image,
				introduction, addTime, clickCount, commentsCount, collectedCount,
				areaAndCountry, deleted, status);
		this.category = category;
		this.path = path;
		this.size = size;
		this.process = process;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getSize() {
		if (size == null) {
			size = 0;
		}
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Float getProcess() {
		if (process == null) {
			process = new Float(0);
		}
		return process;
	}

	public void setProcess(Float process) {
		this.process = process;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

	public Set<Director> getDirectors() {
		return this.directors;
	}

	public void setDirectors(Set<Director> directors) {
		this.directors = directors;
	}

	public Set<Actor> getActors() {
		return this.actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}
	public String toString(){
		return super.toString();
	}
	private static final long serialVersionUID = 9172499910765824488L;
}