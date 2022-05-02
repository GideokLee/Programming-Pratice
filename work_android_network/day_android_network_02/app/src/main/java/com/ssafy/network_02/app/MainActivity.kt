package com.ssafy.network_02.app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.network_02.app.databinding.ActivityMainBinding
import com.ssafy.network_02.app.models.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.round


class MainActivity : AppCompatActivity() {

    companion object {
        private const val API_KEY = "5cbe9dd4041225479ef6d0e088b2ffb8"  //""OPEN WEATHER MAP API KEY"
    }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTemp.setOnClickListener {
            val city = binding.etCity.text.toString()
            getWeatherData(city, API_KEY)
        }
    }

    private fun getWeatherData(city: String, key: String) {
        val weather = ApplicationClass.retrofit.create(Weather::class.java)
        weather.getWeather(city, key).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.code() == 200) {
                    val res = response.body() as WeatherResponse
                    val celsius = res.main.temp - 273.15
                    val result = round(celsius * 100) / 100  // 소수 둘째자리까지
                    binding.tvTemp.text = "${result}도"
                    binding.tvResponseBody.text = res.toString()
                }
                else {
                    Log.d("tag", "getWeatherData - onResponse : Error code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.d("tag", t.message ?: "통신오류")
            }
        })
    }
}