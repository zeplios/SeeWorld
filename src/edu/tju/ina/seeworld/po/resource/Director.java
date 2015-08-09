package edu.tju.ina.seeworld.po.resource;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Director entity. @author MyEclipse Persistence Tools
 */

public class Director implements java.io.Serializable {

	// Fields
	private Integer id;
	private String name;
	private String alias;
	private Timestamp addTime;
	private Set<Movie> movies = new HashSet<Movie>(0);
	private Set<Serial> serials = new HashSet<Serial>(0);

	private AreaAndCountry areaAndCountry;

	// Constructors

	/** default constructor */
	public Director() {
	}
	
	public Director(String name) {
		this.name = name;
	}

	/** full constructor */
	public Director(String name, String alias, 
			Timestamp addTime, Set<Movie> movies, AreaAndCountry anc) {
		this.name = name;
		this.alias = alias;
		this.addTime = addTime;
		this.movies = movies;
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

	public AreaAndCountry getAreaAndCountry() {
		return areaAndCountry;
	}

	public void setAreaAndCountry(AreaAndCountry areaAndCountry) {
		this.areaAndCountry = areaAndCountry;
	}

	public Set<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}

	public Set<Serial> getSerials() {
		return serials;
	}

	public void setSerials(Set<Serial> serials) {
		this.serials = serials;
	}

	private static final long serialVersionUID = 7227080976991120482L;
}