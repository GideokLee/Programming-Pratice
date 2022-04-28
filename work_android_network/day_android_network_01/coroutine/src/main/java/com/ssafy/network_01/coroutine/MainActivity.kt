package com.ssafy.network_01.coroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.network_01.coroutine.d_android_coroutine.CoroutineScopeActivity
import com.ssafy.network_01.coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            btn01.setOnClickListener {
                startActivity(Intent(baseContext, CountDownActivity::class.java))
            }

            btn02.setOnClickListener {
                startActivity(Intent(baseContext, CoroutineScopeActivity::class.java))
            }
        }
    }
}