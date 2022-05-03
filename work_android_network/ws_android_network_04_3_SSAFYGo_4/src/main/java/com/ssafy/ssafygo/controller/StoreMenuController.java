package com.ssafy.ssafygo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafygo.model.dto.StoreMenu;
import com.ssafy.ssafygo.model.service.StoreMenuService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/store/menu")
public class StoreMenuController {
	
	@Autowired
	StoreMenuService storeMenuService;
	
	@GetMapping("/{menuId}")
    @ApiOperation(value = "가맹점 메뉴 정보 반환")
    public StoreMenu findStoreMenuByMenuId(@PathVariable String menuId) {
        System.out.println("findStoreMenuByMenuId");
        return storeMenuService.findStoreMenuByMenuId(menuId);
    }
}
