package com.ssafy.comp_07.app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_07.app.databinding.ActivitySeekBarBinding


@SuppressLint("SetTextI18n")
class SeekBarActivity : AppCompatActivity() {

    lateinit var binding : ActivitySeekBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeekBarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.progressTv.text = "onProgressChanged : $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                binding.progressTv.text = "onStartTrackingTouch : ${seekBar!!.progress}"
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                binding.progressTv.text = "onStopTrackingTouch : ${seekBar!!.progress}"
            }

        })


        binding.btn.setOnClickListener {
            startActivity(Intent(this, RatingBarActivity::class.java))
        }
    }
}