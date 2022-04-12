package com.ssafy.comp_03.local_service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.ssafy.comp_03.local_service.databinding.ActivityMainBinding

private const val TAG = "MainActivity_싸피"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            // 서비스에 바인딩 되어있다면 서비스의 메서드 호출 가능
            if (MyServiceConnection.isBound) {
                binding.textView.text =
                    MyServiceConnection.myService.getCurrentTime()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")

        if (!MyServiceConnection.isBound) {
            Intent(this, BoundService::class.java).also {
                // 결과로 MyServiceConnection의 onServiceConnected 호출
                bindService(it, MyServiceConnection, Context.BIND_AUTO_CREATE)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")

        if (MyServiceConnection.isBound) {
            // 서비스 바인딩 중지
            unbindService(MyServiceConnection)
            MyServiceConnection.isBound = false
        }
    }
}

// 서비스가 반환한 바인더 객체(service: IBinder)를 이용해
// 서비스에 접속하거나 접속 종료를 처리하는 ServiceConnection 객체를 생성하기 위한 클래스 생성
object MyServiceConnection : ServiceConnection {

    // 바인딩한 서비스 객체
    lateinit var myService: BoundService

    // 바인딩 성공 여부
    var isBound = false

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        Log.d(TAG, "onServiceConnected: ")
        val binder = service as MyLocalBinder
        myService = binder.getService()
        isBound = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        Log.d(TAG, "onServiceDisconnected: ")
        isBound = false
    }
}