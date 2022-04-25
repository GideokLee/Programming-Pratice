package com.ssafy.cleanstore.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssafy.bookstore.book.BookActivity
import com.ssafy.bookstore.databinding.FragmentStoreBinding

class StoreFragment : Fragment() {

    private lateinit var ctx: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentStoreBinding.inflate(inflater, container, false).apply {
            tvStoreName.text = "SSAFY BOOK STORE"
            tvStoreLat.text = "36.10830144233874"
            tvStoreLng.text = "128.41827450414362"

            layoutStoreInfo.setOnClickListener {
                Intent(ctx, BookActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }.root
    }
}