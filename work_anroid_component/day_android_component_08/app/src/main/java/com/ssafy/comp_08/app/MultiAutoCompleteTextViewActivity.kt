package com.ssafy.comp_08.app

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_08.app.databinding.ActivityMultiAutoBinding


class MultiAutoCompleteTextViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMultiAutoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val wordList = mutableListOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, wordList)

        binding = ActivityMultiAutoBinding.inflate(layoutInflater).apply {
            mainMultiAutoCompleteTv.apply {
                setAdapter(adapter)
                setTokenizer(MultiAutoCompleteTextView.CommaTokenizer())
            }
        }

        setContentView(binding.root)
    }
}