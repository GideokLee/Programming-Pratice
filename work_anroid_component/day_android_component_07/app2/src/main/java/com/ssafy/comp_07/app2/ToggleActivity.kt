package com.ssafy.comp_07.app2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssafy.comp_07.app2.databinding.ActivityToggleBinding

class ToggleActivity : AppCompatActivity() {

    lateinit var binding : ActivityToggleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToggleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toggleButton.setOnCheckedChangeListener { buttonView, isChecked ->
            when (isChecked) {
                true -> binding.toggleStateTv.text = "Toggle is checked"

                false -> binding.toggleStateTv.text = "Toggle is unchecked"
            }
        }

        binding.btn.setOnClickListener {
            startActivity(Intent(this, SwitchActivity::class.java))
        }
    }
}