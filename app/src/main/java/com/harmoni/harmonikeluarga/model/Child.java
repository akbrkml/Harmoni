package com.harmoni.harmonikeluarga.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Child{

	@SerializedName("data_degree")
	private List<DataDegreeItem> dataDegree;

	@SerializedName("data_child")
	private List<DataChildItem> dataChild;

	@SerializedName("text")
	private String text;

	@SerializedName("status")
	private boolean status;

	public void setDataDegree(List<DataDegreeItem> dataDegree){
		this.dataDegree = dataDegree;
	}

	public List<DataDegreeItem> getDataDegree(){
		return dataDegree;
	}

	public void setDataChild(List<DataChildItem> dataChild){
		this.dataChild = dataChild;
	}

	public List<DataChildItem> getDataChild(){
		return dataChild;
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