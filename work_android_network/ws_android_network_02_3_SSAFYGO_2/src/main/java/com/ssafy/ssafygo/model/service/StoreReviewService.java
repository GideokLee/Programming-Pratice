package com.ssafy.ssafygo.model.service;

import java.util.List;

import com.ssafy.ssafygo.model.dto.StoreReview;

public interface StoreReviewService {
	// 해당 가맹점 리뷰 조회
    public List<StoreReview> findAllStoreReviewByStoreId(String storeId);

    // 해당 리뷰 조회
    public StoreReview findStoreReviewByReviewId(String reviewId);
    
    // 가맹점 리뷰 등록
    public int registerStoreReview(StoreReview storeReview);

    // 가맹점 리뷰 수정
    public int updateStoreReview(StoreReview storeReview);
    
    // 가맹점 리뷰 삭제
    public int deleteStoreReviewById(String id);
}
