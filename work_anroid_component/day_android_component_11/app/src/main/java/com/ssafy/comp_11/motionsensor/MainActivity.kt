package com.ssafy.comp_11.motionsensor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_1).setOnClickListener{
            var intent = Intent(this, SensorListActivity::class.java);
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_2).setOnClickListener{
            var intent = Intent(this, LightSensorActivity::class.java);
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_3).setOnClickListener{
            var intent = Intent(this, StepDetectorActivity::class.java);
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_4).setOnClickListener{
            var intent = Intent(this, AccelerometerSensorActivity::class.java);
            startActivity(intent)

        }

        findViewById<Button>(R.id.button_5).setOnClickListener{
            var intent = Intent(this, GravitySensorActivity::class.java);
            startActivity(intent)

        }

        findViewById<Button>(R.id.button_6).setOnClickListener{
            var intent = Intent(this, GyroScopeActivity::class.java);
            startActivity(intent)
        }
    }
}