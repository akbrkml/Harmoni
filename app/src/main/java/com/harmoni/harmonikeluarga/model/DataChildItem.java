package com.harmoni.harmonikeluarga.model;

import com.google.gson.annotations.SerializedName;

public class DataChildItem{

	@SerializedName("cc_gender")
	private String ccGender;

	@SerializedName("cc_birth_place")
	private String ccBirthPlace;

	@SerializedName("degree_order")
	private String degreeOrder;

	@SerializedName("degree_name")
	private String degreeName;

	@SerializedName("cc_photo")
	private String ccPhoto;

	@SerializedName("cc_birth_date")
	private String ccBirthDate;

	@SerializedName("cc_name")
	private String ccName;

	@SerializedName("degree_create_date")
	private String degreeCreateDate;

	@SerializedName("cc_age")
	private String ccAge;

	@SerializedName("degree_id")
	private String degreeId;

	@SerializedName("degree_status")
	private String degreeStatus;

	@SerializedName("cc_status")
	private String ccStatus;

	@SerializedName("cc_create_date")
	private String ccCreateDate;

	@SerializedName("cc_id")
	private String ccId;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("cc_number")
	private String ccNumber;

	public void setCcGender(String ccGender){
		this.ccGender = ccGender;
	}

	public String getCcGender(){
		return ccGender;
	}

	public void setCcBirthPlace(String ccBirthPlace){
		this.ccBirthPlace = ccBirthPlace;
	}

	public String getCcBirthPlace(){
		return ccBirthPlace;
	}

	public void setDegreeOrder(String degreeOrder){
		this.degreeOrder = degreeOrder;
	}

	public String getDegreeOrder(){
		return degreeOrder;
	}

	public void setDegreeName(String degreeName){
		this.degreeName = degreeName;
	}

	public String getDegreeName(){
		return degreeName;
	}

	public void setCcPhoto(String ccPhoto){
		this.ccPhoto = ccPhoto;
	}

	public String getCcPhoto(){
		return ccPhoto;
	}

	public void setCcBirthDate(String ccBirthDate){
		this.ccBirthDate = ccBirthDate;
	}

	public String getCcBirthDate(){
		return ccBirthDate;
	}

	public void setCcName(String ccName){
		this.ccName = ccName;
	}

	public String getCcName(){
		return ccName;
	}

	public void setDegreeCreateDate(String degreeCreateDate){
		this.degreeCreateDate = degreeCreateDate;
	}

	public String getDegreeCreateDate(){
		return degreeCreateDate;
	}

	public void setCcAge(String ccAge){
		this.ccAge = ccAge;
	}

	public String getCcAge(){
		return ccAge;
	}

	public void setDegreeId(String degreeId){
		this.degreeId = degreeId;
	}

	public String getDegreeId(){
		return degreeId;
	}

	public void setDegreeStatus(String degreeStatus){
		this.degreeStatus = degreeStatus;
	}

	public String getDegreeStatus(){
		return degreeStatus;
	}

	public void setCcStatus(String ccStatus){
		this.ccStatus = ccStatus;
	}

	public String getCcStatus(){
		return ccStatus;
	}

	public void setCcCreateDate(String ccCreateDate){
		this.ccCreateDate = ccCreateDate;
	}

	public String getCcCreateDate(){
		return ccCreateDate;
	}

	public void setCcId(String ccId){
		this.ccId = ccId;
	}

	public String getCcId(){
		return ccId;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setCcNumber(String ccNumber){
		this.ccNumber = ccNumber;
	}

	public String getCcNumber(){
		return ccNumber;
	}
}