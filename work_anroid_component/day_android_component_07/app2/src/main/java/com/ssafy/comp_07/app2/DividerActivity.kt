package com.ssafy.comp_07.app2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_07.app2.databinding.ActivityDividerBinding

class DividerActivity : AppCompatActivity() {

    lateinit var binding: ActivityDividerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDividerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            startActivity(Intent(this, ImageButtonActivity::class.java))
        }
    }
}