package com.ssafy.movie

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.ssafy.movie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, MovieEditActivity::class.java)

            movieEditActivityLauncher.launch(intent)

        }

        binding.btnLookup.setOnClickListener {
            val intent = Intent(this, MovieInfoActivity::class.java)

            startActivity(intent)
        }

    }
    private val movieEditActivityLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode == Activity.RESULT_OK){
            var intent = it.data
            val title = intent!!.getStringExtra("title")
            binding.editTitle.setText(title)

        }
    }
}