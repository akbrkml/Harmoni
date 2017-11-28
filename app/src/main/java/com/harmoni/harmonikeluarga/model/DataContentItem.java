package com.harmoni.harmonikeluarga.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataContentItem{

	private List<DataContentItem> contentItems;

	@SerializedName("content_reject_reason")
	private String contentRejectReason;

	@SerializedName("is_favorite")
	private boolean isFavorite;

	@SerializedName("content_id")
	private String contentId;

	@SerializedName("degree")
	private String degree;

	@SerializedName("content_hits")
	private String contentHits;

	@SerializedName("content_status")
	private String contentStatus;

	@SerializedName("content_tags")
	private String contentTags;

	@SerializedName("content_title")
	private String contentTitle;

	@SerializedName("content_publish_date")
	private String contentPublishDate;

	@SerializedName("content_alias")
	private String contentAlias;

	@SerializedName("section_id")
	private String sectionId;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("topic")
	private String topic;

	@SerializedName("content_desc")
	private String contentDesc;

	@SerializedName("content_create_date")
	private String contentCreateDate;

	@SerializedName("category")
	private String category;

	@SerializedName("content_image")
	private String contentImage;

	public DataContentItem(List<DataContentItem> list) {
		this.contentItems = list;
	}

	public void setContentRejectReason(String contentRejectReason){
		this.contentRejectReason = contentRejectReason;
	}

	public String getContentRejectReason(){
		return contentRejectReason;
	}

	public void setIsFavorite(boolean isFavorite){
		this.isFavorite = isFavorite;
	}

	public boolean isIsFavorite(){
		return isFavorite;
	}

	public void setContentId(String contentId){
		this.contentId = contentId;
	}

	public String getContentId(){
		return contentId;
	}

	public void setDegree(String degree){
		this.degree = degree;
	}

	public String getDegree(){
		return degree;
	}

	public void setContentHits(String contentHits){
		this.contentHits = contentHits;
	}

	public String getContentHits(){
		return contentHits;
	}

	public void setContentStatus(String contentStatus){
		this.contentStatus = contentStatus;
	}

	public String getContentStatus(){
		return contentStatus;
	}

	public void setContentTags(String contentTags){
		this.contentTags = contentTags;
	}

	public String getContentTags(){
		return contentTags;
	}

	public void setContentTitle(String contentTitle){
		this.contentTitle = contentTitle;
	}

	public String getContentTitle(){
		return contentTitle;
	}

	public void setContentPublishDate(String contentPublishDate){
		this.contentPublishDate = contentPublishDate;
	}

	public String getContentPublishDate(){
		return contentPublishDate;
	}

	public void setContentAlias(String contentAlias){
		this.contentAlias = contentAlias;
	}

	public String getContentAlias(){
		return contentAlias;
	}

	public void setSectionId(String sectionId){
		this.sectionId = sectionId;
	}

	public String getSectionId(){
		return sectionId;
	}

	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setTopic(String topic){
		this.topic = topic;
	}

	public String getTopic(){
		return topic;
	}

	public void setContentDesc(String contentDesc){
		this.contentDesc = contentDesc;
	}

	public String getContentDesc(){
		return contentDesc;
	}

	public void setContentCreateDate(String contentCreateDate){
		this.contentCreateDate = contentCreateDate;
	}

	public String getContentCreateDate(){
		return contentCreateDate;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setContentImage(String contentImage){
		this.contentImage = contentImage;
	}

	public String getContentImage(){
		return contentImage;
	}
}