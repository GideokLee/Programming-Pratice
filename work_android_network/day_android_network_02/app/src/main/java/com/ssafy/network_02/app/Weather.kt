package com.ssafy.network_02.app

import com.ssafy.network_02.app.models.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Weather {

    @GET("weather")
    fun getWeather(@Query("q") q: String,
                   @Query("appid") appid: String
    ): Call<WeatherResponse>

}