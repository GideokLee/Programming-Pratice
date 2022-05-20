package com.ssafy.school.subject;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = -6032750606580254644L;

	public NotFoundException(String msg) {
		super(msg);
	}

	public NotFoundException() {
		this("NotFound예외");
	}
}
