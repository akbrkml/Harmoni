package com.harmoni.harmonikeluarga.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Book{

	@SerializedName("data_book")
	private List<DataBookItem> dataBook;

	@SerializedName("data_category")
	private List<DataCategoryItem> dataCategory;

	@SerializedName("text")
	private String text;

	@SerializedName("status")
	private boolean status;

	public void setDataBook(List<DataBookItem> dataBook){
		this.dataBook = dataBook;
	}

	public List<DataBookItem> getDataBook(){
		return dataBook;
	}

	public void setDataCategory(List<DataCategoryItem> dataCategory){
		this.dataCategory = dataCategory;
	}

	public List<DataCategoryItem> getDataCategory(){
		return dataCategory;
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