package com.ssafy.comp_07.app.progressbar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_07.app.databinding.ActivityProgressBarBinding


class ProgressBarActivity : AppCompatActivity() {

    lateinit var binding : ActivityProgressBarBinding
    lateinit var loadingDialog: LoadingDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadingDialog = LoadingDialog(this)

        // ProgressBar 실행
        binding.progressBarBtn.setOnClickListener {
            loadingDialog.show()
        }

        binding.btn.setOnClickListener {
            startActivity(Intent(this, ProgressBarActivity2::class.java))
        }
    }
}