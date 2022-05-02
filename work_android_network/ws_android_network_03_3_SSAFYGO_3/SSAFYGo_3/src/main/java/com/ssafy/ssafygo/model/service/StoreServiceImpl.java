package com.ssafy.ssafygo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafygo.model.dao.StoreDao;
import com.ssafy.ssafygo.model.dto.Store;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreDao storeDao;
	
	@Override
	public Store findByUid(String id) {
		try {
	    	return storeDao.findById(Integer.parseInt(id));
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
