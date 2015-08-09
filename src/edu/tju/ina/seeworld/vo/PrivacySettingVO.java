package edu.tju.ina.seeworld.vo;

public class PrivacySettingVO {
	private Integer id;
	private String userId;
	private Short typeId;
	private Boolean isConcealed;

	public PrivacySettingVO() {

	}

	public Boolean getIsConcealed() {
		return isConcealed;
	}

	public void setIsConcealed(Boolean isConcealed) {
		this.isConcealed = isConcealed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Short getTypeId() {
		return typeId;
	}

	public void setTypeId(Short typeId) {
		this.typeId = typeId;
	}

}
