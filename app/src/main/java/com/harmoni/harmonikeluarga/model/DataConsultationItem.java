package com.harmoni.harmonikeluarga.model;

import com.google.gson.annotations.SerializedName;

public class DataConsultationItem{

	@SerializedName("consult_answer_text")
	private String consultAnswerText;

	@SerializedName("consult_id")
	private String consultId;

	@SerializedName("ahli_id")
	private String ahliId;

	@SerializedName("consult_create_date")
	private String consultCreateDate;

	@SerializedName("data_child")
	private DataChildItem dataChild;

	@SerializedName("consult_privacy")
	private String consultPrivacy;

	@SerializedName("consult_answer_date")
	private String consultAnswerDate;

	@SerializedName("consult_answer_video")
	private String consultAnswerVideo;

	@SerializedName("consult_question")
	private String consultQuestion;

	@SerializedName("cat_id")
	private String catId;

	@SerializedName("consult_title")
	private String consultTitle;

	@SerializedName("consult_tags")
	private String consultTags;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("consult_status")
	private String consultStatus;

	@SerializedName("consult_answer_audio")
	private String consultAnswerAudio;

	public void setConsultAnswerText(String consultAnswerText){
		this.consultAnswerText = consultAnswerText;
	}

	public String getConsultAnswerText(){
		return consultAnswerText;
	}

	public void setConsultId(String consultId){
		this.consultId = consultId;
	}

	public String getConsultId(){
		return consultId;
	}

	public void setAhliId(String ahliId){
		this.ahliId = ahliId;
	}

	public String getAhliId(){
		return ahliId;
	}

	public void setConsultCreateDate(String consultCreateDate){
		this.consultCreateDate = consultCreateDate;
	}

	public String getConsultCreateDate(){
		return consultCreateDate;
	}

	public void setDataChild(DataChildItem dataChild){
		this.dataChild = dataChild;
	}

	public DataChildItem getDataChild(){
		return dataChild;
	}

	public void setConsultPrivacy(String consultPrivacy){
		this.consultPrivacy = consultPrivacy;
	}

	public String getConsultPrivacy(){
		return consultPrivacy;
	}

	public void setConsultAnswerDate(String consultAnswerDate){
		this.consultAnswerDate = consultAnswerDate;
	}

	public String getConsultAnswerDate(){
		return consultAnswerDate;
	}

	public void setConsultAnswerVideo(String consultAnswerVideo){
		this.consultAnswerVideo = consultAnswerVideo;
	}

	public String getConsultAnswerVideo(){
		return consultAnswerVideo;
	}

	public void setConsultQuestion(String consultQuestion){
		this.consultQuestion = consultQuestion;
	}

	public String getConsultQuestion(){
		return consultQuestion;
	}

	public void setCatId(String catId){
		this.catId = catId;
	}

	public String getCatId(){
		return catId;
	}

	public void setConsultTitle(String consultTitle){
		this.consultTitle = consultTitle;
	}

	public String getConsultTitle(){
		return consultTitle;
	}

	public void setConsultTags(String consultTags){
		this.consultTags = consultTags;
	}

	public String getConsultTags(){
		return consultTags;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setConsultStatus(String consultStatus){
		this.consultStatus = consultStatus;
	}

	public String getConsultStatus(){
		return consultStatus;
	}

	public void setConsultAnswerAudio(String consultAnswerAudio){
		this.consultAnswerAudio = consultAnswerAudio;
	}

	public String getConsultAnswerAudio(){
		return consultAnswerAudio;
	}
}