package com.ssafy.school.region;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ssafy.school.person.Person;

public class RegionMgrImpl implements RegionMgr {
	private List<Region> orgs;
	
	private static RegionMgr instance;

	private RegionMgrImpl() {
		orgs = new ArrayList<Region>();
	}
	
	public static RegionMgr getInstance() {
		if (instance != null) {
			instance = new RegionMgrImpl();
		}
		
		return instance;
	}
	
	@Override
	public void add(Region o) {
		orgs.add(o);
	}
	
	@Override
	public void clear() {
		if (orgs != null)
			orgs.clear();
	}

	@Override
	public List<Region> search() {
		return orgs;
	}
	
	@Override
	public Region search(String name) {
		for (Region o : orgs) {
			if (o.getName().equals(name)) {
				return o;
			}
		}
		return null;
	}

	@Override
	public void deleteByStudentCount(int studentCount) {
		for (int i = 0; i < orgs.size(); i++) {
			if (orgs.get(i).getStudentCount() == studentCount) {
				orgs.remove(i);
			}
		}
	}

	@Override
	public Region search(Region o) {
		for (Region org : orgs) {
			if (org == o) {
				return org;
			}
		}
		return null;
	}
}