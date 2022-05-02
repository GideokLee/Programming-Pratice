package com.ssafy.ssafygo.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafygo.model.dao.StoreReviewDao;
import com.ssafy.ssafygo.model.dto.StoreReview;

@Service
public class StoreReviewServiceImpl implements StoreReviewService {

	@Autowired
	private StoreReviewDao storeReviewDao;
	
	@Override
	public List<StoreReview> findAllStoreReviewByStoreId(String storeId) {
		try {
	    	return storeReviewDao.findAllStoreReviewByStoreId(Integer.parseInt(storeId));
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public StoreReview findStoreReviewByReviewId(String reviewId) {
		try {
			return storeReviewDao.findStoreReviewByReviewId(Integer.parseInt(reviewId));
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int registerStoreReview(StoreReview storeReview) {
		try {
	    	storeReviewDao.registerStoreReview(storeReview);
			return 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updateStoreReview(StoreReview storeReview) {
		try {
	    	storeReviewDao.updateStoreReview(storeReview);
			return 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteStoreReviewById(String id) {
		try {
	    	storeReviewDao.deleteStoreReviewById(id);
			return 0;
		}
		catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
