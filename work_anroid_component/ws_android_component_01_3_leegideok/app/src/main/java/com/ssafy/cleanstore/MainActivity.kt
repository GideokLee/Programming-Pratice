package com.ssafy.cleanstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.ssafy.cleanstore.stuff.StuffActivity

class MainActivity : AppCompatActivity() {
    private lateinit var layout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        layout = findViewById(R.id.title)

        layout.setOnClickListener {
            val intent = Intent(this, StuffActivity::class.java)
            startActivity(intent)
        }

    }
}