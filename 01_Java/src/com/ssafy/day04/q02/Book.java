package com.ssafy.day04.q02;

import java.io.Serializable;

public class Book implements Serializable{
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private String desc;
	private int quantity;
	
	
	public Book() {
		isbn = null;
		title = null;
		author = null;
		publisher = null;
		price = 0;
		desc = null;
		quantity = 0;
	}
	public Book(String isbn, String title, String author, String publisher, int price, String desc, int quantity) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.desc = desc;
		this.quantity = quantity;
	}
	

	@Override
	public String toString() {
		return isbn + "\t |  " + title +  "\t | " + author +  "\t|  " + publisher
				+  "\t | " + price +  " |  " + desc +  "\t|  " + quantity;
	}

	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
