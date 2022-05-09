package com.lg.conn.testbleplayer

import android.Manifest
import android.app.Activity
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
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanResult
import android.content.pm.PackageManager
import android.widget.Toast
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class MainActivity : Activity(), View.OnClickListener {

    private var blueAdapter: BluetoothAdapter? = null
    private var blueDevice: BluetoothDevice? = null
    private var blueGatt: BluetoothGatt? = null
    private var remCtrEt: EditText? = null
    private var curStatTview: TextView? = null
    private var bleRemoteConName: String? = null

    //new code ----
    private var leScnner: BluetoothLeScanner? = null

    var UUID_KEY_SERV = UUID
        .fromString("0000ffe0-0000-1000-8000-00805f9b34fb")
    var UUID_KEY_DATA = UUID
        .fromString("0000ffe1-0000-1000-8000-00805f9b34fb")

    private val PERMISSIONS_CODE = 100

    // 모든 퍼미션 관련 배열
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //BLE Data 패킷을 받기 위한 권한 필요 (runtime 시 체크)
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this,
                requiredPermissions,
                PERMISSIONS_CODE
            )
        }

        setContentView(R.layout.activity_main)
        blueAdapter = BluetoothAdapter.getDefaultAdapter()

        leScnner = blueAdapter!!.bluetoothLeScanner

        remCtrEt = findViewById<View>(R.id.remoteConEdtext) as EditText
        curStatTview = findViewById<View>(R.id.curStateTextView) as TextView

        // 이벤트 소스에 이벤트 핸들러 등록
        findViewById<View>(R.id.regRemCBut).setOnClickListener(this)
        findViewById<View>(R.id.connRemCBut).setOnClickListener(this)
        findViewById<View>(R.id.playBut).setOnClickListener(this)
        findViewById<View>(R.id.stopBut).setOnClickListener(this)
    }

    override fun onResume() {
        // TODO Auto-generated method stub
        super.onResume()
        if (!blueAdapter!!.isEnabled) {
            val i = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(i, 1)
        }
    }

    override fun onPause() {
        // TODO Auto-generated method stub
        super.onPause()
        stopBleScan()
    }

    // 위치 정보 권한 요청 결과 콜백 함수
    // ActivityCompat.requestPermissions 실행 이후 실행
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSIONS_CODE -> {
                if(grantResults.isNotEmpty()) {
                    for((i, permission) in permissions.withIndex()) {
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            //권한 획득 실패
                            showMessage("$permission 권한 획득에 실패하였습니다.")
                            finish()
                        }
                    }
                }
            }
        }
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.regRemCBut -> {
                bleRemoteConName = remCtrEt!!.text.toString().trim { it <= ' ' }
                showMessage("$bleRemoteConName 등록되었습니다...")
            }
            R.id.connRemCBut -> srtBleScan()
            R.id.playBut -> {

            }R.id.stopBut -> {
                val playS = Intent(this@MainActivity, MyPlayService::class.java)
                stopService(playS)
            }else -> {

            }
        }
    }

    private fun srtBleScan() {
        //blueAdapter.getBluetoothLeScanner().startScan(sCallback);
        //blueAdapter!!.startLeScan(lecallBack)

        leScnner!!.startScan(leScanCBack)
        showMessage("검색을 시작합니다.........")
    }

    var sCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            // TODO Auto-generated method stub
            super.onScanResult(callbackType, result)
        }
    }

    private fun stopBleScan() {
        //blueAdapter!!.stopLeScan(lecallBack)

        leScnner!!.stopScan(leScanCBack)
        showMessage("검색을 종료합니다......")

        if (blueGatt != null) {
            blueGatt!!.disconnect()
            blueGatt = null
        }
    }

    // Depreciated Code ------
    var lecallBack: LeScanCallback = object : LeScanCallback {
        override fun onLeScan(device: BluetoothDevice, rssi: Int, scanRecord: ByteArray) {
            Log.e("INFO", "++++++ " + device.address + "++++++++")
            if (device.address == bleRemoteConName) {
                blueDevice = device
                blueAdapter!!.stopLeScan(this)
                //connect 작업 수행....
                blueGatt = blueDevice!!.connectGatt(this@MainActivity, false, gattCallback)
            }
        }
    }

    private  val leScanCBack = object : ScanCallback(){
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            val remDevice = result!!.device
            if (remDevice.address == bleRemoteConName) {
                blueDevice = remDevice
                leScnner!!.stopScan(this)
                //connect 작업 수행....
                blueGatt = blueDevice!!.connectGatt(this@MainActivity, false, gattCallback)
            }
        }
    }


    // BluetoothGattCallback 구현.....
    var gattCallback: BluetoothGattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            // TODO Auto-generated method stub
            super.onConnectionStateChange(gatt, status, newState)
            // 연결되면...Ble sensor가 제공하는 모드 서비스 정보를 가져온다..
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                runOnUiThread {
                    showMessage("리모콘 장비와 연결되었습니다..")
                    blueGatt!!.discoverServices()
                }
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            // TODO Auto-generated method stub
            super.onServicesDiscovered(gatt, status)
            // Notification을 받을 수 있도록 센서를 활성화 시킨다..
            Log.e("INFO", "onServicesDiscovered called.....")
            enableKeySensor()
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt,
            value: BluetoothGattCharacteristic
        ) {
            // TODO Auto-generated method stub
            super.onCharacteristicChanged(gatt, value)
            Log.e("INFO", "onCharacteristicChanged called.....")
            getCharacteristic(value)
        }
    }

    private fun enableKeySensor() {
        var keyGattCData: BluetoothGattCharacteristic? = null
        keyGattCData = blueGatt!!.getService(UUID_KEY_SERV).getCharacteristic(UUID_KEY_DATA)
        blueGatt!!.setCharacteristicNotification(keyGattCData, true)
        val descriptor = keyGattCData.getDescriptor(
            UUID
                .fromString("00002902-0000-1000-8000-00805f9b34fb")
        )
        if (descriptor != null) {
            val `val` = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            descriptor.value = `val`
            blueGatt!!.writeDescriptor(descriptor)
        }
        Log.e("INFO", "enableKeySensor() called.....")
    }

    fun getCharacteristic(ch: BluetoothGattCharacteristic?) {
        if (blueAdapter == null || blueGatt == null || ch == null) {
            return
        }
        val uuid = ch.uuid
        val rawValue = ch.value
        val value = Conversion1.BytetohexString(rawValue, rawValue.size)
        runOnUiThread {
            if (value == "01") {
                val playS = Intent(this@MainActivity, MyPlayService::class.java)
                startService(playS)
                curStatTview!!.text = "동진이가 노래 부르는중...."
            } else if (value == "02") {
                val playS = Intent(this@MainActivity, MyPlayService::class.java)
                stopService(playS)
                curStatTview!!.text = "동진이가 노래 멈춤...."
            } else {
            }
        }
    }

    private fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        curStatTview!!.text = msg
    }

    var myH: Handler = object : Handler() {}
}