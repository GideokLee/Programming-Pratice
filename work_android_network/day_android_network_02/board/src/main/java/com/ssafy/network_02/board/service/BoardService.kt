package com.ssafy.network_02.board.service

import com.ssafy.network_02.board.model.Board
import retrofit2.Call
import retrofit2.http.*


interface BoardService {

    @GET("api/board")
    fun selectAll(): Call<MutableList<Board>>

    @GET("api/board/{no}")
    fun selectBoard(@Path("no") no: String): Call<Board>

    @DELETE("api/board/{no}")
    fun deleteBoard(@Path("no") no: String): Call<Unit>

    @PUT("api/board/{no}")
    fun updateBoard(@Path("no") no: String, @Body board: Board): Call<Unit>

    @POST("api/board")
    fun insertBoard(@Body board: Board): Call<Unit>

}