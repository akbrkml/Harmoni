package com.harmoni.harmonikeluarga.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class EventJounalism{

	@SerializedName("data_event")
	private List<DataEventItem> dataEvent;

	@SerializedName("data_participant")
	private List<DataParticipantItem> dataParticipantItems;

	@SerializedName("text")
	private String text;

	@SerializedName("data_journalism")
	private List<DataJournalismItem> dataJournalism;

	@SerializedName("status")
	private boolean status;

	@SerializedName("emId")
	private String emId;

	public void setDataEvent(List<DataEventItem> dataEvent){
		this.dataEvent = dataEvent;
	}

	public List<DataEventItem> getDataEvent(){
		return dataEvent;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public String getEmId(){
		return emId;
	}

	public void setDataJournalism(List<DataJournalismItem> dataJournalism){
		this.dataJournalism = dataJournalism;
	}

	public List<DataJournalismItem> getDataJournalism(){
		return dataJournalism;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	public List<DataParticipantItem> getDataParticipantItems() {
		return dataParticipantItems;
	}

	public void setDataParticipantItems(List<DataParticipantItem> dataParticipantItems) {
		this.dataParticipantItems = dataParticipantItems;
	}
}