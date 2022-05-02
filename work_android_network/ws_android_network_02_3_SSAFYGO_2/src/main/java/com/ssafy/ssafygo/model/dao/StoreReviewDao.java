package com.ssafy.ssafygo.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ssafy.ssafygo.model.dto.StoreReview;

@Repository
public class StoreReviewDao {
	Map<Integer, StoreReview> storeReviewMap = new HashMap<>();
	
	int lastIndex = 3;
	public StoreReviewDao() {
		storeReviewMap.put(1, new StoreReview(1,"다음에도 찾을 것 같아요",4,1));
		storeReviewMap.put(2, new StoreReview(2,"너무 오래 기다려야 하네요..",1,1));
		storeReviewMap.put(3, new StoreReview(3,"오래 기다렸지만 맛은 좋아요",3,1));
	}
	
	// 해당 가맹점 리뷰 모두 조회
    public List<StoreReview> findAllStoreReviewByStoreId(int storeId) throws Exception {
    	return new ArrayList<StoreReview>(storeReviewMap.values());
    }
    
    // 해당 리뷰 조회
    public StoreReview findStoreReviewByReviewId(int reviewId) throws Exception {
    	return storeReviewMap.get(reviewId);
    }
    
    // 가맹점 리뷰 등록
    public void registerStoreReview(StoreReview storeReview) throws Exception {
    	storeReview.setId(++lastIndex);
		storeReviewMap.put(lastIndex, storeReview);
    }

    // 가맹점 리뷰 수정
    public void updateStoreReview(StoreReview storeReview) throws Exception {
    	int reviewId = storeReview.getId();
    	if(storeReviewMap.containsKey(reviewId)) {
        	storeReviewMap.put(reviewId, storeReview);
    	}
    	else {
    		throw new Exception();
    	}
    }
    
    // 가맹점 리뷰 삭제
    public void deleteStoreReviewById(String id) throws Exception{
    	int reviewId = Integer.parseInt(id);
    	if(storeReviewMap.containsKey(reviewId)) {
        	storeReviewMap.remove(reviewId);
    	}
    	else {
    		throw new Exception();
    	}
    }
}
