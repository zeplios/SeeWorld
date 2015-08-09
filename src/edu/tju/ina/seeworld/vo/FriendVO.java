package edu.tju.ina.seeworld.vo;

public class FriendVO {
	private Integer id;
	private String offerId;
	private String theOtherId;
	private String offerName;
	private String theOtherName;
	private String theOtherPhoto;
	private String theOtherAcademy;
	private String theOtherSpecialty;

	public FriendVO() {
	}

	public FriendVO(String oneId,String theOtherId) {
		this.offerId=oneId;
		this.theOtherId=theOtherId;
	}

	
	public String getTheOtherAcademy() {
		return theOtherAcademy;
	}

	public void setTheOtherAcademy(String theOtherAcademy) {
		this.theOtherAcademy = theOtherAcademy;
	}

	public String getTheOtherSpecialty() {
		return theOtherSpecialty;
	}

	public void setTheOtherSpecialty(String theOtherSpecialty) {
		this.theOtherSpecialty = theOtherSpecialty;
	}

	public String getTheOtherId() {
		return theOtherId;
	}

	public void setTheOtherId(String theOtherId) {
		this.theOtherId = theOtherId;
	}

	public String getTheOtherPhoto() {
		return theOtherPhoto;
	}

	public void setTheOtherPhoto(String theOtherPhoto) {
		this.theOtherPhoto = theOtherPhoto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOfferId() {
		return offerId;
	}

	public void setOfferId(String offerId) {
		this.offerId = offerId;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getTheOtherName() {
		return theOtherName;
	}

	public void setTheOtherName(String theOtherName) {
		this.theOtherName = theOtherName;
	}
}
