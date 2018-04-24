package com.harmoni.harmonikeluarga.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 12/3/2017.
 */

public class DataParticipantItem {

    @SerializedName("customer_name")
    public String customerName;

    @SerializedName("customer_image")
    public String customerImage;

    @SerializedName("em_winner_title")
    public String emWinnerTitle;

    @SerializedName("em_winner_desc")
    public String emWinnerDesc;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage;
    }

    public String getEmWinnerTitle() {
        return emWinnerTitle;
    }

    public void setEmWinnerTitle(String emWinnerTitle) {
        this.emWinnerTitle = emWinnerTitle;
    }

    public String getEmWinnerDesc() {
        return emWinnerDesc;
    }

    public void setEmWinnerDesc(String emWinnerDesc) {
        this.emWinnerDesc = emWinnerDesc;
    }
}
