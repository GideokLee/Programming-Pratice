package com.ssafy.network_01.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.network_01.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            btn01.setOnClickListener {
                startActivity(Intent(baseContext, StartActivity::class.java))
            }

            btn02.setOnClickListener {
                startActivity(Intent(baseContext, StartWithHandlerActivity::class.java))
            }

            btn03.setOnClickListener {
                startActivity(Intent(baseContext, ImageActivity::class.java))
            }

            btn04.setOnClickListener {
                startActivity(Intent(baseContext, TimerActivity::class.java))
            }

            btn05.setOnClickListener {
                startActivity(Intent(baseContext, RunOnUiThreadActivity::class.java))
            }

            btn06.setOnClickListener {
                startActivity(Intent(baseContext, AsyncTaskActivity::class.java))
            }
        }
    }
}