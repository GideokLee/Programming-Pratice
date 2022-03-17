package com.ssafy.school.region;

import java.util.List;

import com.ssafy.school.person.Person;

public interface RegionMgr {
	
	void add(Region o);
	
	void clear();
	
	List<Region> search();
	
	Region search(String name);
	
	public void deleteByStudentCount(int employeeCount);

	Region search(Region o);
}