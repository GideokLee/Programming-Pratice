package com.ssafy.ssafygo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafygo.model.dto.Store;
import com.ssafy.ssafygo.model.dto.StoreReview;
import com.ssafy.ssafygo.model.service.StoreReviewService;
import com.ssafy.ssafygo.model.service.StoreService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	StoreService storeService;

	@Autowired
	StoreReviewService storeReviewService;
	
	@GetMapping("/{storeId}")
    @ApiOperation(value = "가맹점 정보 반환")
    public Store findByUid(@PathVariable String storeId) {
        return storeService.findByUid(storeId);
    }

	@GetMapping("/{storeId}/reviews")
    @ApiOperation(value = "가맹점 리뷰 정보 모두 반환")
    public List<StoreReview> findAllStoreReviewByStoreId(@PathVariable String storeId) {
        System.out.println("findAllStoreReviewByStoreId");
        return storeReviewService.findAllStoreReviewByStoreId(storeId);
    }
}
