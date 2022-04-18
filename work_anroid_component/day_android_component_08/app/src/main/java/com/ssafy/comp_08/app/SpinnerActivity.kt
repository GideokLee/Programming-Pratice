package com.ssafy.comp_08.app

import android.R
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_08.app.databinding.ActivitySpinnerBinding


class SpinnerActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpinnerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val idolList = arrayOf("아이유", "브레이브걸스", "방탄소년단", "SG워너비", "블랙핑크", "아이즈원",
            "샤이니", "트와이스", "ITZY", "오마이걸")
        val spinnerAdapter = ArrayAdapter(this, R.layout.simple_spinner_dropdown_item, idolList)

        binding = ActivitySpinnerBinding.inflate(layoutInflater).apply {
            mainSpinner.adapter = spinnerAdapter
        }

        binding.mainSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@SpinnerActivity,
                    "position: $position, value : ${idolList[position]}", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        setContentView(binding.root)
    }
}
