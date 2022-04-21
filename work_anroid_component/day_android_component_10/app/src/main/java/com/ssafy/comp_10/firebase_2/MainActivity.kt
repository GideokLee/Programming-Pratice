package com.ssafy.comp_10.firebase_2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

private const val TAG = "MainActivity_싸피"
class MainActivity : AppCompatActivity() {

    companion object {
        // Notification Channel ID
        const val channel_id = "ssafy_channel"

        // RetroFit 수업 후 Network 에 업로드 할 수 있도록 구성
        fun uploadToken(token: String) {

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // FCM 토큰 수신
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "FCM 토큰 얻기에 실패하였습니다.", task.exception)
                return@OnCompleteListener
            }

            // 새로운 FCM 등록 토큰을 얻음
            uploadToken(task.result!!)

            // token log 남기기
            Log.d(TAG, "token: ${task.result ?: "task.result is null"}")
        })

        createNotificationChannel(channel_id, "ssafy")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    // Notification 수신을 위한 채널 추가
    private fun createNotificationChannel(id: String, name: String) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(id, name, importance)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}