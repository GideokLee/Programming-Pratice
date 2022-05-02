package com.ssafy.ssafygo.service

import com.ssafy.ssafygo.dto.StoreMenuDTO
import com.ssafy.ssafygo.dto.StoreReviewDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StoreMenuService {
    // 매뉴 정보 전부 가져오기
    @GET("store/{storeId}/menus")
    fun getStoreMenus(@Path("storeId") storeId: Int): Call<List<StoreMenuDTO>>

    // 해당 매뉴 정보 가져오기
    @GET("store/menu/{menuId}")
    fun getStoreMenu(@Path("menuId") reviewId: Int): Call<StoreMenuDTO>

}