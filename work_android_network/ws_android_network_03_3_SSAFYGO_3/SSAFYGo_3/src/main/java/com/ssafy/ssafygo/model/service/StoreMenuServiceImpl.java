package com.ssafy.ssafygo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafygo.model.dao.StoreMenuDao;
import com.ssafy.ssafygo.model.dto.StoreMenu;

@Service
public class StoreMenuServiceImpl implements StoreMenuService {

	@Autowired
	private StoreMenuDao storeMenuDao;

	@Override
	public List<StoreMenu> findAllStoreMenuByStoreId(String storeId) {
		try {
	    	return storeMenuDao.findAllStoreMenuByStoreId(Integer.parseInt(storeId));
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public StoreMenu findStoreMenuByMenuId(String menuId) {
		try {
			return storeMenuDao.findStoreMenuByMenuId(Integer.parseInt(menuId));
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
