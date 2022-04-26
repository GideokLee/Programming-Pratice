package com.ssafy.jetpack_02.live_data2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.jetpack_02.live_data2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}