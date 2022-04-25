package com.ssafy.cleanstore.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ssafy.bookstore.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMainBinding.inflate(inflater, container, false).apply {
            tvMainStoreName.text = "SSAFY BOOK STORE"
            tvMainStoreCount.text = "1개"
        }.root
    }
}