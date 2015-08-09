package edu.tju.ina.seeworld.vo;

public class CollectionVO {
	public Integer id;
	private Integer targetId;
	private String addtime;
	private String resourceId;
	private String ownerId;
	private String targetImage;
	private String targetTitle;
	
	public CollectionVO() {
	}

	public String getTargetImage() {
		return targetImage;
	}

	public void setTargetImage(String targetImage) {
		this.targetImage = targetImage;
	}

	public String getTargetTitle() {
		return targetTitle;
	}

	public void setTargetTitle(String targetTitle) {
		this.targetTitle = targetTitle;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

}
