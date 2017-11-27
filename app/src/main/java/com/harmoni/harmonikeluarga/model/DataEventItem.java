package com.harmoni.harmonikeluarga.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataEventItem{

	@SerializedName("event_open_status")
	private String eventOpenStatus;

	@SerializedName("event_desc")
	private String eventDesc;

	@SerializedName("was_join")
	private boolean wasJoin;

	@SerializedName("event_id")
	private String eventId;

	@SerializedName("event_type")
	private List<String> eventType;

	@SerializedName("event_end_date")
	private String eventEndDate;

	@SerializedName("event_name")
	private String eventName;

	@SerializedName("event_image")
	private String eventImage;

	@SerializedName("event_create_date")
	private String eventCreateDate;

	@SerializedName("event_start_date")
	private String eventStartDate;

	@SerializedName("participant")
	private String participant;

	@SerializedName("event_status")
	private String eventStatus;

	public void setEventOpenStatus(String eventOpenStatus){
		this.eventOpenStatus = eventOpenStatus;
	}

	public String getEventOpenStatus(){
		return eventOpenStatus;
	}

	public void setEventDesc(String eventDesc){
		this.eventDesc = eventDesc;
	}

	public String getEventDesc(){
		return eventDesc;
	}

	public void setWasJoin(boolean wasJoin){
		this.wasJoin = wasJoin;
	}

	public boolean isWasJoin(){
		return wasJoin;
	}

	public void setEventId(String eventId){
		this.eventId = eventId;
	}

	public String getEventId(){
		return eventId;
	}

	public void setEventType(List<String> eventType){
		this.eventType = eventType;
	}

	public List<String> getEventType(){
		return eventType;
	}

	public void setEventEndDate(String eventEndDate){
		this.eventEndDate = eventEndDate;
	}

	public String getEventEndDate(){
		return eventEndDate;
	}

	public void setEventName(String eventName){
		this.eventName = eventName;
	}

	public String getEventName(){
		return eventName;
	}

	public void setEventImage(String eventImage){
		this.eventImage = eventImage;
	}

	public String getEventImage(){
		return eventImage;
	}

	public void setEventCreateDate(String eventCreateDate){
		this.eventCreateDate = eventCreateDate;
	}

	public String getEventCreateDate(){
		return eventCreateDate;
	}

	public void setEventStartDate(String eventStartDate){
		this.eventStartDate = eventStartDate;
	}

	public String getEventStartDate(){
		return eventStartDate;
	}

	public void setParticipant(String participant){
		this.participant = participant;
	}

	public String getParticipant(){
		return participant;
	}

	public void setEventStatus(String eventStatus){
		this.eventStatus = eventStatus;
	}

	public String getEventStatus(){
		return eventStatus;
	}
}