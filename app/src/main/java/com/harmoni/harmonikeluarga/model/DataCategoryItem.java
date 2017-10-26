package com.harmoni.harmonikeluarga.model;

import com.google.gson.annotations.SerializedName;

public class DataCategoryItem{

	@SerializedName("cat_image")
	private String catImage;

	@SerializedName("cat_status")
	private String catStatus;

	@SerializedName("cat_level")
	private String catLevel;

	@SerializedName("section_id")
	private String sectionId;

	@SerializedName("cat_name")
	private String catName;

	@SerializedName("cat_alias")
	private String catAlias;

	@SerializedName("cat_desc")
	private String catDesc;

	@SerializedName("cat_hits")
	private String catHits;

	@SerializedName("cat_id")
	private String catId;

	@SerializedName("cat_root")
	private String catRoot;

	@SerializedName("cat_parent")
	private String catParent;

	@SerializedName("cat_order")
	private String catOrder;

	public void setCatImage(String catImage){
		this.catImage = catImage;
	}

	public String getCatImage(){
		return catImage;
	}

	public void setCatStatus(String catStatus){
		this.catStatus = catStatus;
	}

	public String getCatStatus(){
		return catStatus;
	}

	public void setCatLevel(String catLevel){
		this.catLevel = catLevel;
	}

	public String getCatLevel(){
		return catLevel;
	}

	public void setSectionId(String sectionId){
		this.sectionId = sectionId;
	}

	public String getSectionId(){
		return sectionId;
	}

	public void setCatName(String catName){
		this.catName = catName;
	}

	public String getCatName(){
		return catName;
	}

	public void setCatAlias(String catAlias){
		this.catAlias = catAlias;
	}

	public String getCatAlias(){
		return catAlias;
	}

	public void setCatDesc(String catDesc){
		this.catDesc = catDesc;
	}

	public String getCatDesc(){
		return catDesc;
	}

	public void setCatHits(String catHits){
		this.catHits = catHits;
	}

	public String getCatHits(){
		return catHits;
	}

	public void setCatId(String catId){
		this.catId = catId;
	}

	public String getCatId(){
		return catId;
	}

	public void setCatRoot(String catRoot){
		this.catRoot = catRoot;
	}

	public String getCatRoot(){
		return catRoot;
	}

	public void setCatParent(String catParent){
		this.catParent = catParent;
	}

	public String getCatParent(){
		return catParent;
	}

	public void setCatOrder(String catOrder){
		this.catOrder = catOrder;
	}

	public String getCatOrder(){
		return catOrder;
	}
}