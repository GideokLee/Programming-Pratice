package com.ssafy.ssafygo.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.ssafygo.model.dto.StoreMenu;

@Repository
public class StoreMenuDao {
	Map<Integer, StoreMenu> storeMenuMap = new HashMap<>();
	
	int lastIndex = 3;
	public StoreMenuDao() {
		storeMenuMap.put(1, new StoreMenu(1,"아메리카노",1600,1));
		storeMenuMap.put(2, new StoreMenu(2,"민트초코 라떼",3000,1));
		storeMenuMap.put(3, new StoreMenu(3,"바닐라 라떼",2500,1));
		storeMenuMap.put(4, new StoreMenu(4,"초코칩 쿠키",2000,1));
		storeMenuMap.put(5, new StoreMenu(5,"카페모카",3500,1));
	}
	
	// 해당 가맹점 메뉴 모두 조회
    public List<StoreMenu> findAllStoreMenuByStoreId(int storeId) throws Exception {
    	return new ArrayList<StoreMenu>(storeMenuMap.values());
    }
    
    // 해당 메뉴 조회
    public StoreMenu findStoreMenuByMenuId(int reviewId) throws Exception {
    	return storeMenuMap.get(reviewId);
    }
}
