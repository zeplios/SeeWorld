package edu.tju.ina.seeworld.vo;

import edu.tju.ina.seeworld.po.resource.Multimedia;
import edu.tju.ina.seeworld.util.DateUtil;

public class MultimediaVOForView extends SimpleMultimedia{
	private String year;
	private String addTime;
	private String introduction;
	private String areaAndCountry;
	private Integer clickCount;
	private Integer recommendedCount;
	private Integer collectedCount;
	private Integer commentsCount;

	public MultimediaVOForView(Multimedia multimedia) {
		super(multimedia);
		if (multimedia != null) {
			year = multimedia.getPublishedYear();
			addTime = DateUtil.getFormattedDateString(multimedia.getAddTime())
					.substring(0, 10);
			if(multimedia.getAreaAndCountry() != null)
				areaAndCountry= multimedia.getAreaAndCountry().getName();
			introduction = multimedia.getIntroduction();
			clickCount = multimedia.getClickCount();
			recommendedCount = multimedia.getRecommendedCount();
			collectedCount = multimedia.getCollectedCount();
			commentsCount = multimedia.getComments().size();
		}
	}

	public String getIntroduction() {
		return introduction;
	}

	public String getAreaAndCountry() {
		return areaAndCountry;
	}

	public void setAreaAndCountry(String areaAndCountry) {
		this.areaAndCountry = areaAndCountry;
	}

	public String getYear() {
		return year;
	}

	public String getAddTime() {
		return addTime;
	}

	public Integer getClickCount() {
		return clickCount;
	}

	public Integer getRecommendedCount() {
		return recommendedCount;
	}

	public Integer getCollectedCount() {
		return collectedCount;
	}

	public Integer getCommentsCount() {
		return commentsCount;
	}
	
}
