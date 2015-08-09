package edu.tju.ina.seeworld.vo;

import edu.tju.ina.seeworld.po.resource.Mainpage;
import edu.tju.ina.seeworld.util.DateUtil;

public class MainpageVO {
	
	private Integer id;
	private MultimediaVOForView multimedia;
	private String briefDescription;
	private String image;
	private Boolean isScroll;
	private Boolean isStatic;
	private String addTime;
	
	public MainpageVO(Mainpage m) {
		this.id = m.getId();
		this.multimedia = new MultimediaVOForView(m.getMultimedia());
		this.briefDescription = m.getBriefDescription();
		this.image = m.getImage();
		this.isScroll = m.getIsScroll();
		this.isStatic = m.getIsStatic();
		this.addTime = DateUtil.getFormattedDateString(m.getAddTime()).substring(0, 10);;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MultimediaVOForView getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(MultimediaVOForView multimedia) {
		this.multimedia = multimedia;
	}

	public String getBriefDescription() {
		return briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Boolean getIsScroll() {
		return isScroll;
	}

	public void setIsScroll(Boolean isScroll) {
		this.isScroll = isScroll;
	}

	public Boolean getIsStatic() {
		return isStatic;
	}

	public void setIsStatic(Boolean isStatic) {
		this.isStatic = isStatic;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
}
