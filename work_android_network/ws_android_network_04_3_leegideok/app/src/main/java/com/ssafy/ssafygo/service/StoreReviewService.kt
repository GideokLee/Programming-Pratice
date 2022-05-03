package com.ssafy.ssafygo.service

import com.ssafy.ssafygo.dto.StoreReviewDTO
import retrofit2.Call
import retrofit2.http.*

interface StoreReviewService {
    // 리뷰 정보 전부 가져오기
    @GET("store/{storeId}/reviews")
    fun getStoreReviews(@Path("storeId") storeId: Int): Call<List<StoreReviewDTO>>

    // 해당 리뷰 정보 가져오기
    @GET("store/review/{reviewId}")
    fun getStoreReview(@Path("reviewId") reviewId: Int): Call<StoreReviewDTO>

    // 리뷰 정보 등록
    @POST("store/review")
    fun setStoreReview(@Body body: StoreReviewDTO): Call<Boolean>

    // 리뷰 정보 수정하기
    @PUT("store/review/{id}")
    fun putStoreReview(@Path("id") id: Int, @Body body: StoreReviewDTO): Call<Boolean>

    // 리뷰 정보 삭제하기
    @DELETE("store/review/{id}")
    fun deleteStoreReview(@Path("id") id: Int): Call<Boolean>
}