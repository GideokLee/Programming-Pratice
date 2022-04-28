package com.ssafy.network_01.coroutine.d_android_coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ssafy.network_01.coroutine.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutineScopeActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_scope)

        mJob = Job()
        val textview = findViewById<TextView>(R.id.textView)

        mJob = launch {
            // 1. 데이터 입출력을 해야 하므로 IO Dispatcher에 배분
            val deferredInt: Deferred<Array<Int>> = async(Dispatchers.IO) {
                arrayOf(3, 1, 2, 4, 5)
            }

            // 2. Sorting. CPU 작업으로 Default Dispatcher에 배분
            val sortedDeferred = async(Dispatchers.Default) {
                val value = deferredInt.await()
                value.sortedBy { it }
            }

            // 3. TextView에 세팅하는 것은 UI 작업이므로 Main Dispatcher에 배분
            val textViewSettingJob = launch {
                val sortedArray = sortedDeferred.await()
                textview.text = sortedArray.toString()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }
}