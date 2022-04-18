package com.ssafy.comp_08.app2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_08.app2.databinding.ActivityViewStubBinding


class ViewStubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewStubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewStubBinding.inflate(layoutInflater).apply {
            mainBtnShow.setOnClickListener {
                mainStub.visibility = View.VISIBLE
            }

            mainBtnHide.setOnClickListener {
                mainStub.visibility = View.GONE
            }
        }

        setContentView(binding.root)
    }
}
