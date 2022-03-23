package com.ssafy.ws.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// DTO : Data Transfer Object
@ApiModel(value = "Book : 도서 정보", description = "도서의 상세 정보를 나타낸다.")
public class Book {
	@ApiModelProperty(value = "도서 번호")
	private String isbn;
	
	@ApiModelProperty(value = "제목")
	private String title;
	
	@ApiModelProperty(value = "작가")
	private String author;
	
	@ApiModelProperty(value = "가격")
	private Integer price;
	
	@ApiModelProperty(value = "내용")
	private String content;
	
	@ApiModelProperty(value = "이미지 경로")
	private String img;
	
	public Book() {}

	public Book(String isbn, String title, String author, Integer price, String content, String img) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.content = content;
		this.img = img;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [isbn=").append(isbn).append(", title=").append(title).append(", author=").append(author)
				.append(", price=").append(price).append(", content=").append(content).append(", img=").append(img)
				.append("]");
		return builder.toString();
	}

}
