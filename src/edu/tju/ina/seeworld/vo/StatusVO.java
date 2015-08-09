package edu.tju.ina.seeworld.vo;

public class StatusVO {
	private String userId;
	private String photoPath;
	private int id;
	private String status;
	private String addTime;
	
	public StatusVO(){
	
	}
	
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public  String getUserId() {
		return userId;
	}
	public  void setUserId(String userId) {
		this.userId = userId;
	}
	public  int getId() {
		return id;
	}
	public  void setId(int id) {
		this.id = id;
	}
	public  String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public  String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
}
