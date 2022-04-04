package com.ssafy.myapplication

import android.util.Log

private const val TAG = "CarNew_클래스"
class CarNew() : Car {
    override fun introduce(): String {
        Log.d(TAG, "introduce: SDK 30이상 버전에서 동작하는 자동차")
        return "introduce: SDK 30이상 버전에서 동작하는 자동차"
    }
}