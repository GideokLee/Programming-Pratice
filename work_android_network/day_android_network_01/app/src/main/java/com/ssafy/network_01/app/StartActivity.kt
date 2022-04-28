package com.ssafy.network_01.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ssafy.network_01.app.databinding.ActivityStartBinding

private const val TAG = "StartActivity_싸피"
class StartActivity : AppCompatActivity() {

    private var isRunning = false
    private lateinit var binding: ActivityStartBinding

    inner class ThreadClass : Thread() {
        override fun run() {
            while (isRunning) {
                sleep(100)
                Log.d(TAG, System.currentTimeMillis().toString())
                // Only the original thread that created a view hierarchy can touch its views.
                //binding.helloTextView.text = System.currentTimeMillis().toString()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isRunning = true
        val thread = ThreadClass()
        thread.start()
    }

//    override fun onRestart() {
//        super.onRestart()
//        isRunning = true
//    }
//
//    override fun onStop() {
//        super.onStop()
//        isRunning = false
//    }
}