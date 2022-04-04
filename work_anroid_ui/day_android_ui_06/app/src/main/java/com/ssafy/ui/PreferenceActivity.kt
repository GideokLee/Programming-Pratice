package com.ssafy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PreferenceActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        tvResult = findViewById(R.id.tv_result)

        val prefs = getPreferences(MODE_PRIVATE)
        //val prefs = getSharedPreferences("PreferenceActivity", MODE_PRIVATE)

        // 정보 추출하기
        val v = prefs.getInt("KEY", 0)

        // 정보 저장하기
        val editor = prefs.edit()
        editor.putInt("KEY", v.toInt() + 1)
        editor.commit()

        tvResult.text = "결과: $v"
    }
}