package com.ssafy.school.subject;

public interface SubjectMgr {
	void add(Subject v) throws DuplicatedException;

	Subject[] search();

	Subject search(String name) throws NotFoundException;
}