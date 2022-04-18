package com.ssafy.comp_07.app2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_07.app2.databinding.ActivityImageButtonBinding

class ImageButtonActivity : AppCompatActivity() {

    lateinit var binding : ActivityImageButtonBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageButton1.setOnTouchListener { v, event ->
            when( event.action ) {
                MotionEvent.ACTION_DOWN -> {
                    Toast.makeText(this, "ACTION_DOWN", Toast.LENGTH_SHORT).show()
                }
                MotionEvent.ACTION_UP -> {
                    Toast.makeText(this, "ACTION_UP", Toast.LENGTH_SHORT).show()
                }
            }
            // onTouch -> onClick 이벤트가 되는데, 뒤에 이벤트를 안한다: true, 한다 : false
            false
        }

        binding.btn.setOnClickListener {
            startActivity(Intent(this, ChipActivity::class.java))
        }
    }
}