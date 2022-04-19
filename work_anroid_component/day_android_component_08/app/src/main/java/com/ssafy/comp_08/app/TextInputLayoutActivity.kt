package com.ssafy.comp_08.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_08.app.databinding.ActivityTextInputLayoutBinding


class TextInputLayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTextInputLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTextInputLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}