package com.harmoni.harmonikeluarga.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataTopicItem{

	@SerializedName("data_content")
	private List<DataContentItem> dataContent;

	@SerializedName("is_topic")
	private boolean isTopic;

	@SerializedName("topic_create_date")
	private String topicCreateDate;

	@SerializedName("topic_order")
	private String topicOrder;

	@SerializedName("topic_name")
	private String topicName;

	@SerializedName("topic_id")
	private String topicId;

	@SerializedName("topic_image")
	private String topicImage;

	@SerializedName("topic_status")
	private String topicStatus;

	public DataTopicItem(List<DataContentItem> contentItems){
		this.dataContent = contentItems;
	}

	public void setIsTopic(boolean isTopic){
		this.isTopic = isTopic;
	}

	public boolean isIsTopic(){
		return isTopic;
	}

	public void setTopicCreateDate(String topicCreateDate){
		this.topicCreateDate = topicCreateDate;
	}

	public String getTopicCreateDate(){
		return topicCreateDate;
	}

	public void setTopicOrder(String topicOrder){
		this.topicOrder = topicOrder;
	}

	public String getTopicOrder(){
		return topicOrder;
	}

	public void setTopicName(String topicName){
		this.topicName = topicName;
	}

	public String getTopicName(){
		return topicName;
	}

	public void setTopicId(String topicId){
		this.topicId = topicId;
	}

	public String getTopicId(){
		return topicId;
	}

	public void setTopicImage(String topicImage){
		this.topicImage = topicImage;
	}

	public String getTopicImage(){
		return topicImage;
	}

	public void setTopicStatus(String topicStatus){
		this.topicStatus = topicStatus;
	}

	public String getTopicStatus(){
		return topicStatus;
	}

	public void setDataContent(List<DataContentItem> dataContent){
		this.dataContent = dataContent;
	}

	public List<DataContentItem> getDataContent(){
		return dataContent;
	}
}