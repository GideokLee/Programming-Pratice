package com.ssafy.comp_07.app.progressbar

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.ssafy.comp_07.app.databinding.DialogLoadingBinding


class LoadingDialog(context: Context) : Dialog(context) {

    lateinit var binding : DialogLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 타이틀바 제거
//        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = DialogLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 배경 투명하게
        window!!.setBackgroundDrawable(ColorDrawable())
    }
}