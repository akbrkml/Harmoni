package com.harmoni.harmonikeluarga.model;

import com.google.gson.annotations.SerializedName;

public class DataUser{

	@SerializedName("user_status")
	private String userStatus;

	@SerializedName("show_id")
	private int showId;

	@SerializedName("customer_name")
	private String customerName;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("customer_code")
	private String customerCode;

	@SerializedName("customer_image")
	private String customerImage;

	@SerializedName("customer_reg_id")
	private String customerRegId;

	@SerializedName("customer_min")
	private String customerMin;

	@SerializedName("customer_mdn")
	private String customerMdn;

	@SerializedName("customer_msisdn")
	private String customerMsisdn;

	@SerializedName("customer_handset")
	private String customerHandset;

	@SerializedName("customer_provider")
	private String customerProvider;

	@SerializedName("customer_email")
	private String customerEmail;

	@SerializedName("customer_password")
	private String customerPassword;

	@SerializedName("customer_birth_place")
	private String customerBirthPlace;

	@SerializedName("customer_birth_date")
	private String customerBirthDate;

	@SerializedName("customer_gender")
	private String customerGender;

	@SerializedName("customer_address")
	private String customerAddress;

	@SerializedName("customer_city")
	private String customerCity;

	@SerializedName("customer_province")
	private String customerProvince;

	@SerializedName("customer_postcode")
	private String customerPostcode;

	@SerializedName("customer_status")
	private String customerStatus;

	@SerializedName("customer_create_date")
	private String customerCreateDate;

	public String getCustomerRegId() {
		return customerRegId;
	}

	public void setCustomerRegId(String customerRegId) {
		this.customerRegId = customerRegId;
	}

	public String getCustomerMin() {
		return customerMin;
	}

	public void setCustomerMin(String customerMin) {
		this.customerMin = customerMin;
	}

	public String getCustomerMdn() {
		return customerMdn;
	}

	public void setCustomerMdn(String customerMdn) {
		this.customerMdn = customerMdn;
	}

	public String getCustomerMsisdn() {
		return customerMsisdn;
	}

	public void setCustomerMsisdn(String customerMsisdn) {
		this.customerMsisdn = customerMsisdn;
	}

	public String getCustomerHandset() {
		return customerHandset;
	}

	public void setCustomerHandset(String customerHandset) {
		this.customerHandset = customerHandset;
	}

	public String getCustomerProvider() {
		return customerProvider;
	}

	public void setCustomerProvider(String customerProvider) {
		this.customerProvider = customerProvider;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerBirthPlace() {
		return customerBirthPlace;
	}

	public void setCustomerBirthPlace(String customerBirthPlace) {
		this.customerBirthPlace = customerBirthPlace;
	}

	public String getCustomerBirthDate() {
		return customerBirthDate;
	}

	public void setCustomerBirthDate(String customerBirthDate) {
		this.customerBirthDate = customerBirthDate;
	}

	public String getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(String customerGender) {
		this.customerGender = customerGender;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerProvince() {
		return customerProvince;
	}

	public void setCustomerProvince(String customerProvince) {
		this.customerProvince = customerProvince;
	}

	public String getCustomerPostcode() {
		return customerPostcode;
	}

	public void setCustomerPostcode(String customerPostcode) {
		this.customerPostcode = customerPostcode;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getCustomerCreateDate() {
		return customerCreateDate;
	}

	public void setCustomerCreateDate(String customerCreateDate) {
		this.customerCreateDate = customerCreateDate;
	}

	public void setUserStatus(String userStatus){
		this.userStatus = userStatus;
	}

	public String getUserStatus(){
		return userStatus;
	}

	public void setShowId(int showId){
		this.showId = showId;
	}

	public int getShowId(){
		return showId;
	}

	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}

	public String getCustomerName(){
		return customerName;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setCustomerCode(String customerCode){
		this.customerCode = customerCode;
	}

	public String getCustomerCode(){
		return customerCode;
	}

	public void setCustomerImage(String customerImage){
		this.customerImage = customerImage;
	}

	public String getCustomerImage(){
		return customerImage;
	}
}