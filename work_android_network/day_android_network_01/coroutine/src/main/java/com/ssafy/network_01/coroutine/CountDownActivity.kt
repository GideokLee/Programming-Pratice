package com.ssafy.network_01.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.network_01.coroutine.databinding.ActivityCountDownBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val TAG = "CountDownActivity_싸피"
class CountDownActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountDownBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCountDownBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                10.countDown(++currentIndex)
            }
        }
    }

    var currentIndex = 0

    private suspend fun Int.countDown(currentIndex: Int) {
        for (index in this downTo 1) {
            binding.textView.text = "Now index $currentIndex Countdown $index"
            delay(1000)
        }
        Log.i (TAG, "Now index $currentIndex Done!")
    }

}