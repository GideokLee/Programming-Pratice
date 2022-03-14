package com.ssafy.book.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CommentDto : ���� �������� �ۼ��ϴ� ������ ���� ������")
public class CommentDto {

	@ApiModelProperty(value = "�۹�ȣ")
	private int comment_no;
	@ApiModelProperty(value = "�ۼ����̸�")
	private String user_name;
	@ApiModelProperty(value = "������")
	private String comment;
	@ApiModelProperty(value = "�ۼ��ð�")
	private String comment_time;
	@ApiModelProperty(value = "å������ȣ", example = "111-111-1111")
	private String isbn;

	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return "CommentDto [comment_no=" + comment_no + ", user_name=" + user_name + ", comment=" + comment
				+ ", comment_time=" + comment_time + ", isbn=" + isbn + "]";
	}

}
