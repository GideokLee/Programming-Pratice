package com.ssafy.comp_03.notification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_simple).setOnClickListener{
            var intent = Intent(this, SimpleNotification::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btn_picture).setOnClickListener{
            var intent = Intent(this, PictureNotification::class.java)
            startActivity(intent)
        }
    }
}