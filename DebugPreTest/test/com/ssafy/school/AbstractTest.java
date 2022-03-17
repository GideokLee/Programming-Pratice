package com.ssafy.school;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import com.ssafy.school.person.PersonMgr;
import com.ssafy.school.person.PersonMgrImpl;
import com.ssafy.school.region.RegionMgr;
import com.ssafy.school.region.RegionMgrImpl;
import com.ssafy.school.subject.SubjectMgr;
import com.ssafy.school.subject.SubjectMgrImpl;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public abstract class AbstractTest {
	
	RegionMgr omgr = RegionMgrImpl.getInstance();
	
	PersonMgr pmgr = PersonMgrImpl.getInstance();

	SubjectMgr vmgr = new SubjectMgrImpl();
	
}
