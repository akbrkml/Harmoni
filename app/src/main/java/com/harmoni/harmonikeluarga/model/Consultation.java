package com.harmoni.harmonikeluarga.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by akbar on 07/11/17.
 */

public class Consultation {

    @SerializedName("status")
    public boolean status;

    @SerializedName("text")
    public String text;

    @SerializedName("data_consultation")
    public List<DataConsultationItem> dataConsultation;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<DataConsultationItem> getDataConsultation() {
        return dataConsultation;
    }

    public void setDataConsultation(List<DataConsultationItem> dataConsultation) {
        this.dataConsultation = dataConsultation;
    }
}
