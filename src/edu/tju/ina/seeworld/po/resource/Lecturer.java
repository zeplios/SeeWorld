package edu.tju.ina.seeworld.po.resource;

import java.util.HashSet;
import java.util.Set;

/**
 * Lecturer entity. @author Uranus
 */

public class Lecturer implements java.io.Serializable {

	// Fields
	private Integer id;
	private String name;
	private Organization organization;
	private Set<Video> videos = new HashSet<Video>(0);

	// Constructors

	/** default constructor */
	public Lecturer() {
	}

	/** minimal constructor */
	public Lecturer(String name, Organization organisation) {
		this.name = name;
		this.organization = organisation;
	}

	/** full constructor */
	public Lecturer(String name, Organization organisation, Set<Video> videos) {
		this.name = name;
		this.organization = organisation;
		this.videos = videos;
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

	public Organization getOrganization() {
		return this.organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<Video> getVideos() {
		return this.videos;
	}

	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}
	private static final long serialVersionUID = -6136629205963425546L;
}