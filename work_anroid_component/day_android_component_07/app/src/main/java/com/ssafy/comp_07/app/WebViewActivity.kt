package com.ssafy.comp_07.app

import android.content.Intent
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_07.app.databinding.ActivityWebViewBinding


class WebViewActivity : AppCompatActivity() {

    lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()

        binding.webView.loadUrl("https://www.ssafy.com/ksp/jsp/swp/swpMain.jsp")

        binding.go.setOnClickListener {
            var url = binding.url.text.toString()
            binding.webView.loadUrl(url)
        }

        binding.btn.setOnClickListener {
            startActivity(Intent(this, VideoViewActivity::class.java))
        }
    }
}