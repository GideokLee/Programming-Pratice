package com.ssafy.school.subject;

import java.util.Arrays;

public class SubjectMgrImpl implements SubjectMgr {
	private Subject[] virus;
	private int index;

	public SubjectMgrImpl() {
		if(index < 100) {
			virus = new Subject[100];
		}
	}

	@Override
	public void add(Subject v) throws DuplicatedException {
		try {
			search(v.getName());
			
		} catch (NotFoundException e) {
			virus[index++] = v;
		}
	}

	@Override
	public Subject[] search() {
		return virus;
	}

	@Override
	public Subject search(String name) throws NotFoundException {
		for (int i = 0; i < virus.length; i++) {
			if (virus[i].getName().equals(name))
				return virus[i];
		}
		return null;
	}
}
