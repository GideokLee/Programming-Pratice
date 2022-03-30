package com.ssafy.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.movie.databinding.ActivityMovieInfoBinding

class MovieInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            finish()
        }
    }
}