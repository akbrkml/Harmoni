package com.harmoni.harmonikeluarga.model;

import com.google.gson.annotations.SerializedName;

public class User{

	@SerializedName("data_user")
	private DataUser dataUser;

	@SerializedName("text")
	private String text;

	@SerializedName("status")
	private boolean status;

	public void setDataUser(DataUser dataUser){
		this.dataUser = dataUser;
	}

	public DataUser getDataUser(){
		return dataUser;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}