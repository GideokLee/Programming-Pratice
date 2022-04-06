package com.ssafy.memo

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView

class CustomIntro : LinearLayout {
    // 커스텀 뷰 구현하기
    private lateinit var title: TextView

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
        getAttrs(attrs)
    }

    private fun init() {
        val view = LayoutInflater.from(context).inflate(R.layout.custom_intro,
            this, false)
        addView(view)
        title = findViewById(R.id.introTitle)

    }

    private fun getAttrs(attrs: AttributeSet){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomIntro)
        setTypedArray(typedArray)
    }

    private fun setTypedArray(typedArray: TypedArray){
        title.text = typedArray.getText(R.styleable.CustomIntro_introTitle)
        typedArray.recycle()
    }
}