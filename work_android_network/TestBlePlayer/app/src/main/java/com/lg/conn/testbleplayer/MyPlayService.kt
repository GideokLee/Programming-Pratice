package com.lg.conn.testbleplayer

import android.app.Activity
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.widget.EditText
import android.widget.TextView
import android.os.Bundle
import com.lg.conn.testbleplayer.R
import android.content.Intent
import com.lg.conn.testbleplayer.MyPlayService
import android.bluetooth.le.ScanCallback
import android.bluetooth.BluetoothAdapter.LeScanCallback
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothProfile
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.widget.Toast
import android.media.MediaPlayer
import android.os.IBinder

class MyPlayService : Service() {
    var mediaPlayer: MediaPlayer? = null
    override fun onBind(intent: Intent): IBinder? {
        // TODO Auto-generated method stub
        //IntentService
        return null
    }

    override fun onCreate() {
        // TODO Auto-generated method stub
        super.onCreate()
        Toast.makeText(this, "서비스 생성", Toast.LENGTH_SHORT).show()
    }

    //MediaPlayer를 play하는 코드 구현....
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(this, "서비스를 제공합니다....", Toast.LENGTH_SHORT).show()
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.lee_ost)
        with(mediaPlayer) {
            this?.setLooping(false)
            this?.start()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    //MediaPlayer를 stop하는 코드 구현....
    override fun onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy()
        if (mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
        }
        Toast.makeText(this, "서비스 종료", Toast.LENGTH_SHORT).show()
    }
}