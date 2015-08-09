package edu.tju.ina.seeworld.po.resource;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import edu.tju.ina.seeworld.po.rbac.Resource;

/**
 * Video entity. @author MyEclipse Persistence Tools
 */

public class Video extends Multimedia implements java.io.Serializable {

	// Fields
	/**
	 * 如果有分部或者分集的情况，表示一共多少部
	 */
	private Integer sections;
	/**
	 * 表示当前视频是第几部
	 */
	private Integer sectionNum;
	private String path;
	private Integer size;
	private Float process;
	private String sectionTitle;
	private String sectionAlias;
	
	private Set<Category> category = new HashSet<Category>(0);
	private Set<Lecturer> lecturers = new HashSet<Lecturer>(0);

	// Constructors

	/** default constructor */
	public Video() {
	}

	/** minimal constructor */
	public Video(Set<Category> category, Resource resourceType, String title,
			String publishedYear, String path, Integer size, Float process,
			Boolean deleted, Boolean status, Timestamp addTime, Integer sections) {
		super(resourceType, title, publishedYear, addTime);
		this.category = category;
		this.sections = sections;
		this.path = path;
		this.size = size;
		this.process = process;
	}

	/** full constructor */
	public Video(Set<Category> category, Resource resourceType, String title,
			String alias, String publishedYear, String image, String path,
			String introduction, Integer size, Float process, Boolean deleted,
			Boolean status, Timestamp addTime, Integer clickCount, Integer commentsCount,
			Integer collectedCount, AreaAndCountry areaAndCountry,
			Integer sections, Integer sectionNum, Set<Lecturer> lecturers) {
		super(resourceType, title, alias, publishedYear, image,
				introduction, addTime, clickCount, commentsCount, collectedCount,
				areaAndCountry, deleted, status);
		this.category = category;
		this.sections = sections;
		this.sectionNum = sectionNum;
		this.lecturers = lecturers;
		this.path = path;
		this.size = size;
		this.process = process;

	}

	// Property accessors

	public Integer getSections() {
		return this.sections;
	}

	public void setSections(Integer sections) {
		this.sections = sections;
	}

	public Integer getSectionNum() {
		return this.sectionNum;
	}

	public void setSectionNum(Integer sectionNum) {
		this.sectionNum = sectionNum;
	}

	public Set<Lecturer> getLecturers() {
		return this.lecturers;
	}

	public void setLecturers(Set<Lecturer> lecturers) {
		this.lecturers = lecturers;
	}

	public String getSectionAlias() {
		return sectionAlias;
	}

	public void setSectionAlias(String sectionAlias) {
		this.sectionAlias = sectionAlias;
	}

	private static final long serialVersionUID = -7678229087383592763L;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Float getProcess() {
		return process;
	}

	public void setProcess(Float process) {
		this.process = process;
	}

	public String getSectionTitle() {
		return sectionTitle;
	}

	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public void setCategory(Set<Category> category) {
		this.category = category;
	}

	public String toString() {
		String suffix = "";
		if (getSections() != null && getSections() > 1) {
			suffix = " " + getSectionNum();
		}
		return super.toString() + suffix;
	}
}