package com.ssafy.memo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.memo.databinding.ActivityMemoInfoBinding

class MemoInfoActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMemoInfoBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finish()
        }

    }
}