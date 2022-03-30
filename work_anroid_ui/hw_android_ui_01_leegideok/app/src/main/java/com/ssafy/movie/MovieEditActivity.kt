package com.ssafy.movie

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ssafy.movie.databinding.ActivityMovieEditBinding

class MovieEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            if(binding.editTitle.text.isEmpty()){
                Toast.makeText(this, "값을 입력 해주세요.",Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("title", binding.editTitle.text.toString())
                setResult(Activity.RESULT_OK, intent)

                finish()
            }
        }
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
}