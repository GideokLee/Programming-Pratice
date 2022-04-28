package com.ssafy.network_01.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.ssafy.network_01.app.databinding.ActivityStartBinding
import com.ssafy.network_01.app.databinding.ActivityStartWithHandlerBinding

private const val TAG = "WithHandlerActivity_싸피"
class StartWithHandlerActivity : AppCompatActivity() {

    private var isRunning = false
    private lateinit var binding: ActivityStartWithHandlerBinding

    inner class ThreadClass : Thread() {
        override fun run() {
            val handler = Handler(Looper.getMainLooper())

            while (isRunning) {
                sleep(100)
                Log.d(TAG, System.currentTimeMillis().toString())
                handler.post {
                    binding.helloTextView.text = System.currentTimeMillis().toString()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartWithHandlerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isRunning = true
        val thread = ThreadClass()
        thread.start()
    }

    override fun onRestart() {
        super.onRestart()
        isRunning = true
    }

    override fun onStop() {
        super.onStop()
        isRunning = false
    }
}