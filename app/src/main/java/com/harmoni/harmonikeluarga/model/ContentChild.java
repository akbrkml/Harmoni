package com.harmoni.harmonikeluarga.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ContentChild{

	@SerializedName("text")
	private String text;

	@SerializedName("data_topic")
	private List<DataTopicItem> dataTopic;

	@SerializedName("data_content")
	private List<DataContentItem> dataContent;

	@SerializedName("status")
	private boolean status;

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setDataTopic(List<DataTopicItem> dataTopic){
		this.dataTopic = dataTopic;
	}

	public List<DataTopicItem> getDataTopic(){
		return dataTopic;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public List<DataContentItem> getDataContent() {
		return dataContent;
	}

	public void setDataContent(List<DataContentItem> dataContent) {
		this.dataContent = dataContent;
	}
}