package edu.tju.ina.seeworld.vo;

import edu.tju.ina.seeworld.po.user.User;

public class SimpleUserVO {// 简化版的USERVO类
	private String id;
	private String userName;
	private String realName;
	private String photoPath;

	public SimpleUserVO(User user) {
		this.id = user.getId();
		this.realName = user.getRealName();
		this.userName = user.getUsername();
		this.photoPath = user.getPhotoPath();
	}

	public SimpleUserVO() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
}
