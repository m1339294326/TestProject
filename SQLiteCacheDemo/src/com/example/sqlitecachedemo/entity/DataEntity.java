package com.example.sqlitecachedemo.entity;

public class DataEntity {
	private int id;
	private String bookName;
	private String price;
	private String image;
	private String author;
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "DataEntity [bookName=" + bookName + ", price=" + price
				+ ", image=" + image + ", author=" + author + "]";
	}
	
}
