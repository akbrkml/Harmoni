package com.harmoni.harmonikeluarga.model;

import com.google.gson.annotations.SerializedName;

public class DataBookItem{

	@SerializedName("book_status")
	private String bookStatus;

	@SerializedName("cat_name")
	private String catName;

	@SerializedName("book_isbn")
	private String bookIsbn;

	@SerializedName("book_author")
	private String bookAuthor;

	@SerializedName("book_id")
	private String bookId;

	@SerializedName("book_sample_file")
	private String bookSampleFile;

	@SerializedName("book_language")
	private String bookLanguage;

	@SerializedName("book_full_file")
	private String bookFullFile;

	@SerializedName("book_desc")
	private String bookDesc;

	@SerializedName("book_pages")
	private String bookPages;

	@SerializedName("book_title")
	private String bookTitle;

	@SerializedName("cat_id")
	private String catId;

	@SerializedName("book_image")
	private String bookImage;

	@SerializedName("book_publication_year")
	private String bookPublicationYear;

	@SerializedName("book_create_date")
	private String bookCreateDate;

	@SerializedName("book_publisher")
	private String bookPublisher;

	public void setBookStatus(String bookStatus){
		this.bookStatus = bookStatus;
	}

	public String getBookStatus(){
		return bookStatus;
	}

	public void setCatName(String catName){
		this.catName = catName;
	}

	public String getCatName(){
		return catName;
	}

	public void setBookIsbn(String bookIsbn){
		this.bookIsbn = bookIsbn;
	}

	public String getBookIsbn(){
		return bookIsbn;
	}

	public void setBookAuthor(String bookAuthor){
		this.bookAuthor = bookAuthor;
	}

	public String getBookAuthor(){
		return bookAuthor;
	}

	public void setBookId(String bookId){
		this.bookId = bookId;
	}

	public String getBookId(){
		return bookId;
	}

	public void setBookSampleFile(String bookSampleFile){
		this.bookSampleFile = bookSampleFile;
	}

	public String getBookSampleFile(){
		return bookSampleFile;
	}

	public void setBookLanguage(String bookLanguage){
		this.bookLanguage = bookLanguage;
	}

	public String getBookLanguage(){
		return bookLanguage;
	}

	public void setBookFullFile(String bookFullFile){
		this.bookFullFile = bookFullFile;
	}

	public String getBookFullFile(){
		return bookFullFile;
	}

	public void setBookDesc(String bookDesc){
		this.bookDesc = bookDesc;
	}

	public String getBookDesc(){
		return bookDesc;
	}

	public void setBookPages(String bookPages){
		this.bookPages = bookPages;
	}

	public String getBookPages(){
		return bookPages;
	}

	public void setBookTitle(String bookTitle){
		this.bookTitle = bookTitle;
	}

	public String getBookTitle(){
		return bookTitle;
	}

	public void setCatId(String catId){
		this.catId = catId;
	}

	public String getCatId(){
		return catId;
	}

	public void setBookImage(String bookImage){
		this.bookImage = bookImage;
	}

	public String getBookImage(){
		return bookImage;
	}

	public void setBookPublicationYear(String bookPublicationYear){
		this.bookPublicationYear = bookPublicationYear;
	}

	public String getBookPublicationYear(){
		return bookPublicationYear;
	}

	public void setBookCreateDate(String bookCreateDate){
		this.bookCreateDate = bookCreateDate;
	}

	public String getBookCreateDate(){
		return bookCreateDate;
	}

	public void setBookPublisher(String bookPublisher){
		this.bookPublisher = bookPublisher;
	}

	public String getBookPublisher(){
		return bookPublisher;
	}
}