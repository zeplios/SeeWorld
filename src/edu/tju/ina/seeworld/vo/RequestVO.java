package edu.tju.ina.seeworld.vo;

import edu.tju.ina.seeworld.po.user.Request;

public class RequestVO {
	private Integer id;
	private String sender_id;
	private String reciever_id;
	private String senderPhoto;
	private String senderRealName;
	private String senderAcademyName;
	private String senderSpecialtyName;
	
	public RequestVO() {
	}

	public RequestVO(Request r){
		this.setId(r.getId());
		this.setSenderPhoto(r.getSender().getPhotoPath());
		this.setSender_id(r.getSender().getId());
		this.setReciever_id(r.getReciever().getId());
		this.setSenderRealName(r.getSender().getRealName());
		this.setSenderAcademyName(r.getSender().getAcademy()
				.getName());
		this.setSenderSpecialtyName(r.getSender().getSpecialty()
				.getName());
	}
	
	public String getSenderPhoto() {
		return senderPhoto;
	}

	public void setSenderPhoto(String senderPhoto) {
		this.senderPhoto = senderPhoto;
	}

	public String getSenderRealName() {
		return senderRealName;
	}

	public void setSenderRealName(String senderRealName) {
		this.senderRealName = senderRealName;
	}

	public String getSenderAcademyName() {
		return senderAcademyName;
	}

	public void setSenderAcademyName(String senderAcademyName) {
		this.senderAcademyName = senderAcademyName;
	}

	public String getSenderSpecialtyName() {
		return senderSpecialtyName;
	}

	public void setSenderSpecialtyName(String senderSpecialtyName) {
		this.senderSpecialtyName = senderSpecialtyName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSender_id() {
		return sender_id;
	}
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	public String getReciever_id() {
		return reciever_id;
	}
	public void setReciever_id(String reciever_id) {
		this.reciever_id = reciever_id;
	}
	
}
