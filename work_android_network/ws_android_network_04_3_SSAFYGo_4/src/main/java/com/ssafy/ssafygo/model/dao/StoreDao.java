package com.ssafy.ssafygo.model.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.ssafygo.model.dto.Store;

@Repository
public class StoreDao {
	Map<Integer, Store> storeMap = new HashMap<>();
	
	public StoreDao() {
		storeMap.put(1, new Store(1,"싸피벅스", "010-1234-5678", "https://www.ssafy.com/swp/images/sns_img.png", 36.10830144233874, 128.41827450414362));
	}
	
	// 가맹점 조회
    public Store findById(int id) throws Exception {
    	return storeMap.get(id);
    }
}
