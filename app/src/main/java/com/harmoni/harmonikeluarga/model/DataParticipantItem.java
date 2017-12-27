package com.harmoni.harmonikeluarga.model;

import com.google.gson.annotations.SerializedName;

public class DataParticipantItem{

	@SerializedName("customer_name")
	private String customerName;

	@SerializedName("customer_image")
	private String customerImage;

	@SerializedName("em_winner_title")
	private String emWinnerTitle;

	@SerializedName("em_winner_desc")
	private String emWinnerDesc;

	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}

	public String getCustomerName(){
		return customerName;
	}

	public void setCustomerImage(String customerImage){
		this.customerImage = customerImage;
	}

	public String getCustomerImage(){
		return customerImage;
	}

	public String getEmWinnerTitle() {
		return emWinnerTitle;
	}

	public void setEmWinnerTitle(String emWinnerTitle) {
		this.emWinnerTitle = emWinnerTitle;
	}

	public String getEmWinnerDesc() {
		return emWinnerDesc;
	}

	public void setEmWinnerDesc(String emWinnerDesc) {
		this.emWinnerDesc = emWinnerDesc;
	}
}
