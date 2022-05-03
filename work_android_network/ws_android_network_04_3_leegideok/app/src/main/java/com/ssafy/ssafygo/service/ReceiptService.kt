package com.ssafy.ssafygo.service

import com.ssafy.ssafygo.dto.ReceiptDTO
import com.ssafy.ssafygo.dto.StoreReviewDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ReceiptService {
    @GET("receipt/all")
    fun getReceiptAll(): Call<List<ReceiptDTO>>

    // 마지막 영수증 정보 반환
    @GET("receipt")
    fun getReceipt(): Call<ReceiptDTO>

    // 주문내역 등록
    @POST("receipt")
    fun setReceipt(@Body body: ReceiptDTO): Call<Boolean>

}