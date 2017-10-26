package com.harmoni.harmonikeluarga.model;

import com.google.gson.annotations.SerializedName;

public class DataDegreeItem{

	@SerializedName("degree_id")
	private String degreeId;

	@SerializedName("degree_order")
	private String degreeOrder;

	@SerializedName("degree_status")
	private String degreeStatus;

	@SerializedName("degree_name")
	private String degreeName;

	@SerializedName("degree_create_date")
	private String degreeCreateDate;

	public void setDegreeId(String degreeId){
		this.degreeId = degreeId;
	}

	public String getDegreeId(){
		return degreeId;
	}

	public void setDegreeOrder(String degreeOrder){
		this.degreeOrder = degreeOrder;
	}

	public String getDegreeOrder(){
		return degreeOrder;
	}

	public void setDegreeStatus(String degreeStatus){
		this.degreeStatus = degreeStatus;
	}

	public String getDegreeStatus(){
		return degreeStatus;
	}

	public void setDegreeName(String degreeName){
		this.degreeName = degreeName;
	}

	public String getDegreeName(){
		return degreeName;
	}

	public void setDegreeCreateDate(String degreeCreateDate){
		this.degreeCreateDate = degreeCreateDate;
	}

	public String getDegreeCreateDate(){
		return degreeCreateDate;
	}
}