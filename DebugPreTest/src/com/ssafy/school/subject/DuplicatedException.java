package com.ssafy.school.subject;

public class DuplicatedException extends Exception {

	private static final long serialVersionUID = 4187011927144400440L;

	public DuplicatedException(String msg) {
		super(msg);
	}

	public DuplicatedException() {
		this("Duplicated예외");
	}
}
