package com.ssafy.comp_08.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.ssafy.comp_08.app.databinding.ActivityFloatingBinding


class FloatingActionButtonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFloatingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFloatingBinding.inflate(layoutInflater).apply {
            fab.setOnClickListener { view ->
                Snackbar.make(view, "Snackbar Message", Snackbar.LENGTH_SHORT).show()
            }
        }

        setContentView(binding.root)
    }
}
