package com.ssafy.comp_03.aidl_service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "AIDLService_싸피"
class AIDLService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind: ")
        return MyAidlImpl
    }
}

object MyAidlImpl : IMyAidlInterface.Stub() {

    // AIDL 인터페이스에서 정의했던 메서드 override
    override fun getCurrentTime(): String {
        Log.d(TAG, "getCurrentTime: ")
        val dateformat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA)
        dateformat.timeZone = TimeZone.getTimeZone("Asia/Seoul")
        return dateformat.format(Date())
    }
}