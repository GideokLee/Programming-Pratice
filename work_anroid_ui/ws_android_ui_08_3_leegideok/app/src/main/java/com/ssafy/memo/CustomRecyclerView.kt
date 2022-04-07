package com.ssafy.memo

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout

class CustomRecyclerView : LinearLayout {
    // 커스텀 뷰 구현하기
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init(){
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.custom_item, this  , false)
        addView(view)
    }

}