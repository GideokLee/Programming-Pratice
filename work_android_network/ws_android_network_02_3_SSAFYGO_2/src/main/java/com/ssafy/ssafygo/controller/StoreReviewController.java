package com.ssafy.ssafygo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafygo.model.dto.StoreReview;
import com.ssafy.ssafygo.model.service.StoreReviewService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequestMapping("/store/review")
public class StoreReviewController {
	
	@Autowired
	StoreReviewService storeReviewService;
	
	@GetMapping("/{reviewId}")
    @ApiOperation(value = "가맹점 리뷰 정보 반환")
    public StoreReview findStoreReviewByReviewId(@PathVariable String reviewId) {
        System.out.println("findStoreReviewByReviewId");
        return storeReviewService.findStoreReviewByReviewId(reviewId);
    }
	
	@PostMapping("")
    @ApiOperation(value="가맹점 리뷰 등록")
    public boolean registerStoreReview(@RequestBody StoreReview storeReview){
        // 가맹점의 리뷰 등록
        int res = storeReviewService.registerStoreReview(storeReview);
        System.out.println("registerStoreReview");
        
        return res != -1 ? true : false;
    }
	
	@PutMapping("/{id}")
    @ApiOperation(value = "리뷰 정보 수정")
    public boolean updateStoreReview(@RequestBody StoreReview request, @PathVariable String id) {
        int res = storeReviewService.updateStoreReview(request);
        System.out.println("updateStoreReview");
        
        return res != -1 ? true : false;
	}
	
	@DeleteMapping("/{id}")
    @ApiOperation(value = "리뷰 삭제")
    public boolean deleteStoreReviewById(@PathVariable String id) {
        int res = storeReviewService.deleteStoreReviewById(id);
        System.out.println("deleteStoreReview");
        
        return res != -1 ? true : false;
    }
}
