package com.ssafy.comp_07.app

import android.content.Intent
import android.os.Bundle
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_07.app.databinding.ActivityRatingBarBinding


class RatingBarActivity : AppCompatActivity() {

    lateinit var binding : ActivityRatingBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRatingBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ratingBarDefault.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener { ratingBar, rating, fromUser ->
                binding.ratingBarMiddle.rating = rating
                binding.ratingBarSmall.rating = rating
            }

        binding.btn.setOnClickListener {
            startActivity(Intent(this, SearchViewActivity::class.java))
        }
    }
}