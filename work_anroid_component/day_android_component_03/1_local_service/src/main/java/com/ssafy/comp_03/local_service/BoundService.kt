package com.ssafy.comp_03.local_service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "BoundService_싸피"
class BoundService : Service() {

    // 서비스 클라이언트 바인딩 시 반환할 IBinder 객체
    private lateinit var myLocalBinder: IBinder

    // 서비스 클라이언트 바인딩 시 IBinder 객체 반환
    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind: ")
        myLocalBinder = MyLocalBinder()
        return myLocalBinder
    }

    // 서비스 클라이언트가 바인딩 되면 호출할 함수
    fun getCurrentTime(): String {
        Log.d(TAG, "getCurrentTime: ")
        val dateformat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA)
        dateformat.timeZone = TimeZone.getTimeZone("Asia/Seoul")

        return dateformat.format(Date())
    }
}

// Binder는 IBinder의 구현체로 onBind를 통해 서비스 클라이언트에게 전달되며
// 클라이언트는 이 객체를 이용해 서비스에 선언된 기능을 호출
class MyLocalBinder : Binder() {
    // 외부 객체인 BoundService 객체를 반환하는 함수
    fun getService(): BoundService = BoundService()
}