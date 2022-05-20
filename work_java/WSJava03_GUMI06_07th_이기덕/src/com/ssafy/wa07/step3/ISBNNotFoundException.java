package com.ssafy.wa07.step3;

public class ISBNNotFoundException extends Exception{
	
	private String isbn;
	
	public ISBNNotFoundException(String isbn) {
		super("해당 도서의 ISBN이 존재하지 않습니다.");
		this.isbn = isbn;
	}

	public String getIsbn(){
		return isbn;
	}
}
