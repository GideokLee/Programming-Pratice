package com.ssafy.myapplication

import android.os.Build

object CarFactory {

    fun getCar(): Car {
        return when (Build.VERSION.SDK_INT) {
            in 30 .. 32 -> CarNew()
            else -> CarOld()
        }
    }
}