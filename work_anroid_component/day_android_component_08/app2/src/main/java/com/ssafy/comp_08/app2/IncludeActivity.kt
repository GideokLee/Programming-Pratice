package com.ssafy.comp_08.app2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_08.app2.databinding.ActivityIncludeBinding


class IncludeActivity: AppCompatActivity() {

    lateinit var binding: ActivityIncludeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIncludeBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}