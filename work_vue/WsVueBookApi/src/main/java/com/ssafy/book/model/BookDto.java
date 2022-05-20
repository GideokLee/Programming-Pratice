package com.ssafy.book.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BookDto : å �ѱ��� ����", description = "å�� �� ������ ��Ÿ����.")
public class BookDto {

	@ApiModelProperty(value = "������ȣ", example = "111-111-1111")
	private String isbn;
	@ApiModelProperty(value = "å����")
	private String title;
	@ApiModelProperty(value = "����")
	private String author;
	@ApiModelProperty(value = "����", example = "10000")
	private int price;
	@ApiModelProperty(value = "������")
	private String content;

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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BookDto [isbn=" + isbn + ", title=" + title + ", author=" + author + ", price=" + price + ", content="
				+ content + "]";
	}

}
