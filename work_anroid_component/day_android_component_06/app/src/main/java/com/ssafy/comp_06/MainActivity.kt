package com.ssafy.comp_06

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_1).setOnClickListener {
            var intent = Intent(this, GpsNetworkActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_2).setOnClickListener {
            var intent = Intent(this, MyLocationActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_3).setOnClickListener {
            var intent = Intent(this, PlacesAPIActivity::class.java)
            startActivity(intent)
        }
    }
}