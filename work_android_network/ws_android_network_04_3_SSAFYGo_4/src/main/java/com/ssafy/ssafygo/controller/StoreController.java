package com.ssafy.ssafygo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafygo.model.dto.Store;
import com.ssafy.ssafygo.model.dto.StoreMenu;
import com.ssafy.ssafygo.model.service.StoreMenuService;
import com.ssafy.ssafygo.model.service.StoreService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	StoreService storeService;

	@Autowired
	StoreMenuService storeMenuService;
	
	@GetMapping("/{storeId}")
    @ApiOperation(value = "가맹점 정보 반환")
    public Store findByUid(@PathVariable String storeId) {
        System.out.println("findByUid");
        return storeService.findByUid(storeId);
    }

	@GetMapping("/{storeId}/menus")
    @ApiOperation(value = "가맹점 메뉴 정보 모두 반환")
    public List<StoreMenu> findAllStoreMenuByStoreId(@PathVariable String storeId) {
        System.out.println("findAllStoreMenuByStoreId");
        return storeMenuService.findAllStoreMenuByStoreId(storeId);
    }
}
