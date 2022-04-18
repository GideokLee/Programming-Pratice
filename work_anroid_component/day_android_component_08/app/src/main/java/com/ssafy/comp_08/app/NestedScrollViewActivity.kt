package com.ssafy.comp_08.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.comp_08.app.databinding.ActivityNestedScrollViewBinding


class NestedScrollViewActivity  : AppCompatActivity() {

    private lateinit var binding: ActivityNestedScrollViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myAdapter = MyAdapter(
            arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve")
        )
        binding = ActivityNestedScrollViewBinding.inflate(layoutInflater).apply {
            mainRv2.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = myAdapter
            }
        }
        setContentView(binding.root)
    }
}
