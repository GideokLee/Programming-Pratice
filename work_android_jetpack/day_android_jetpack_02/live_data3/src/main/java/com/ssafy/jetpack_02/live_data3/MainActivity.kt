package com.ssafy.jetpack_02.live_data3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.ssafy.jetpack_02.live_data3.databinding.ActivityMainBinding

private const val TAG = "MainActivity_싸피"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var timerLiveData: TimerLiveData
    private lateinit var observer: Observer<Long>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timerLiveData = TimerLiveData()

        observer = Observer {
            binding.tvTimer.text = it.toString()
        }

        // 1.
        timerLiveData.observe(this, observer)

        // 2. LifeCycle과 관계없이 Observer가 데이터를 받게 하려면 아래 코드를 사용
//        timerLiveData.observeForever(observer)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")

        // 3. 옵저버를 제거할 때는 removeObserver를 사용
//        timerLiveData.removeObserver(observer)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }
}