package com.ssafy.ssafygo.model.service;

import java.util.List;

import com.ssafy.ssafygo.model.dto.StoreMenu;

public interface StoreMenuService {
	// 해당 가맹점 메뉴 조회
    public List<StoreMenu> findAllStoreMenuByStoreId(String storeId);

    // 해당 메뉴 조회
    public StoreMenu findStoreMenuByMenuId(String menuId);
}
