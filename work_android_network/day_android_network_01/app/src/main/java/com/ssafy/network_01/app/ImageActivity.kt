package com.ssafy.network_01.app

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.network_01.app.databinding.ActivityImageBinding


class ImageActivity : AppCompatActivity() {

    private val dogImageList = ArrayList<Int>()

    lateinit var binding : ActivityImageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        for (i in 1..30) {
            dogImageList.add(R.drawable.dog1)
            dogImageList.add(R.drawable.dog2)
            dogImageList.add(R.drawable.dog3)
        }

        val handler = Handler(Looper.getMainLooper())
        Thread {
            for (i in dogImageList) {
                Thread.sleep(1000)
                handler.post {
                    binding.dogIv.setImageResource(i)
                }
            }
        }.start()
    }
}