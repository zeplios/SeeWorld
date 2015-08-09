package edu.tju.ina.seeworld.vo;

import org.apache.commons.lang.StringUtils;

import edu.tju.ina.seeworld.po.resource.Category;
import edu.tju.ina.seeworld.po.resource.Lecturer;
import edu.tju.ina.seeworld.po.resource.Video;

public class VideoVOForView extends MultimediaVOForView {

	private String path;
	private Integer sections;
	private Integer sectionNum;
	private Integer size;
	private String sectionTitle;
	private String sectionAlias;
	private String lecturers = "";
	private String categorys = "";

	public VideoVOForView(Video v) {
		super(v);
		if (v != null) {
			sections = v.getSections();
			sectionNum = v.getSectionNum();
			size = v.getSize();
			path = v.getPath();
			sectionTitle = v.getSectionTitle();
			sectionAlias = v.getSectionAlias();
			for (Lecturer l : v.getLecturers()) {
				lecturers += l.getName() + "|";
			}
			for (Category c : v.getCategory()) {
				categorys += c.getName() + "|";
			}
		}
	}

	public String getPath() {
		return path;
	}

	public Integer getSections() {
		return sections;
	}

	public Integer getSectionNum() {
		return sectionNum;
	}

	public Integer getSize() {
		return size;
	}

	public String getSectionTitle() {
		return sectionTitle;
	}

	public String getSectionAlias() {
		return sectionAlias;
	}

	public String getLecturers() {
		if (StringUtils.isNotBlank(lecturers)) {
			lecturers = lecturers.substring(0, lecturers.length() - 1);
		}
		return lecturers;
	}

	public String getCategorys() {
		if (StringUtils.isNotBlank(categorys)) {
			categorys = categorys.substring(0, categorys.length() - 1);
		}
		return categorys;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setSections(Integer sections) {
		this.sections = sections;
	}

	public void setSectionNum(Integer sectionNum) {
		this.sectionNum = sectionNum;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public void setSectionTitle(String sectionTitle) {
		this.sectionTitle = sectionTitle;
	}

	public void setSectionAlias(String sectionAlias) {
		this.sectionAlias = sectionAlias;
	}

	public void setLecturers(String lecturers) {
		this.lecturers = lecturers;
	}
}
