package edu.tju.ina.seeworld.vo;

import org.apache.commons.lang.StringUtils;

import edu.tju.ina.seeworld.po.resource.Actor;
import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.resource.Director;
import edu.tju.ina.seeworld.po.resource.Movie;

/**
 * 在页面显示时的Movie VO对象
 * 
 * @author Uranus
 */
public class MovieVOForView extends MultimediaVOForView {

	private String path;
	private Integer size;
	private String actors = "";
	private String directors = "";
	private String categorys = "";

	public MovieVOForView(Movie m) {
		super(m);
		if (m != null) {
			path=m.getPath();
			size = m.getSize();
			for (Actor a : m.getActors()) {
				if (StringUtils.isNotBlank(a.getName())) {
					actors += a.getName() + "|";
				}
			}
			for (Director d : m.getDirectors()) {
				if (StringUtils.isNotBlank(d.getName())) {
					directors += d.getName() + "|";
				}
			}
			for (Category c : m.getCategory()) {
				if (StringUtils.isNotBlank(c.getName())) {
					categorys += c.getName() + "|";
				}
			}
		}
	}

	public String getPath() {
		return path;
	}

	public Integer getSize() {
		return size;
	}

	public String getActors() {
		if (StringUtils.isNotBlank(actors)) {
			actors = actors.substring(0, actors.length() - 1);
		}
		return actors;
	}

	public String getDirectors() {
		if (StringUtils.isNotBlank(directors)) {
			directors = directors.substring(0, directors.length() - 1);
		}
		return directors;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getCategorys() {
		if (StringUtils.isNotBlank(categorys)) {
			categorys = categorys.substring(0, categorys.length() - 1);
		}
		return categorys;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

}
