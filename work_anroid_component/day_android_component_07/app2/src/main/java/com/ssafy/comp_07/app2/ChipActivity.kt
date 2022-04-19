package com.ssafy.comp_07.app2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.ssafy.comp_07.app2.databinding.ActivityChipBinding

class ChipActivity : AppCompatActivity() {

    lateinit var binding : ActivityChipBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChipBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = arrayListOf("Option1", "Option2", "Option3",
            "Option4", "Option5", "Option6", "Option7")

        for (i in list) {
            val chip = Chip(this)
            chip.text = i
            chip.isCheckable = true
            binding.chipGroupOptions.addView(chip)
        }

        binding.actionChip.setOnClickListener {
            Toast.makeText(this, "action chip clicked", Toast.LENGTH_SHORT).show()
        }

        binding.inputChip.setOnClickListener {
            Toast.makeText(this, "inputChip  clicked", Toast.LENGTH_SHORT).show()
        }

        binding.inputChip.setOnCloseIconClickListener {
            Toast.makeText(this, "inputChip close clicked", Toast.LENGTH_SHORT).show()
        }



        binding.btn.setOnClickListener {
            startActivity(Intent(this, CheckBoxActivity::class.java))
        }
    }


}