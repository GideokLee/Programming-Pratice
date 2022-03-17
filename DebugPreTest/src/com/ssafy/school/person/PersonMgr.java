package com.ssafy.school.person;

import java.io.IOException;
import java.util.List;

public interface PersonMgr {
	
	void add(Person p);
	
	void clear();

	List<Person> search();

	void load(String fileName) throws IOException;

	void save(String fileName) throws IOException;

	List<Person> orderByName();
	
}