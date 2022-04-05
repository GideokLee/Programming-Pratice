package com.ssafy.ws.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// DTO : Data Transfer Object
@ApiModel(value = "Board : 게시물 정보", description = "게시물의 상세 정보를 나타낸다.")
public class Board {
	
	@ApiModelProperty(value = "게시물 번호")
	private int no;
	
	@ApiModelProperty(value = "제목")
	private String title;
	
	@ApiModelProperty(value = "내용")
	private String content;
	
	@ApiModelProperty(value = "작성자")
	private String writer;
	
	@ApiModelProperty(value = "작성일")
	private Date regtime;
	
	public Board() {}

	public Board(int no, String title, String content, String writer, Date regtime) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regtime = regtime;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegtime() {
		return regtime;
	}
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	@Override
	public String toString() {
		return "Board [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer + ", regtime="
				+ regtime + "]";
	}
	
}	