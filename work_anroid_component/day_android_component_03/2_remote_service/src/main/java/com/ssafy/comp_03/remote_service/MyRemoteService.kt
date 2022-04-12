package com.ssafy.comp_03.remote_service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.*
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class IncomingHandler(var context: Context) : Handler(
    Looper.myLooper()!! // 현재 스레드에 할당된 looper 객체 반환
) {
    // 메시지를 수신했을 때 처리할 동작 구현
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        val data = msg.data
        val str = data.getString("MyString")

        val service = context as MyRemoteService
        Toast.makeText(context,
            "$str \n ${service.getFormattedDate()}",
            Toast.LENGTH_SHORT)
            .show()
    }
}

private const val TAG = "MyRemoteService_싸피"
class MyRemoteService : Service() {

    private lateinit var messenger: Messenger

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: ")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind: ")
        messenger = Messenger(IncomingHandler(this))
        return messenger.binder
    }

    fun getFormattedDate() : String {
        val format = SimpleDateFormat("yyyy-MM-dd kk:mm:ss E", Locale.KOREAN) //format 설정
        format.timeZone = TimeZone.getTimeZone("Asia/Seoul") //TimeZone  설정 (GMT +9)

        //현재시간에 적용
        return format.format(Date().time)
    }
}