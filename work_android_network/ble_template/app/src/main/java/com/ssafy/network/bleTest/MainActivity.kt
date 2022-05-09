package com.ssafy.network.bleTest

import android.Manifest
import android.bluetooth.*
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var bluetoothAdapter: BluetoothAdapter
    private lateinit var bluetoothDevice: BluetoothDevice
    //센서와 get, set, notify...
    private lateinit var bluetoothGatt: BluetoothGatt

    private lateinit var macEt:EditText
    private lateinit var infoTv:TextView
    private lateinit var macStr:String

    //스캔관련 객체
    private lateinit var leScanner: BluetoothLeScanner


    private val PERMISSIONS_CODE = 100

    // 모든 퍼미션 관련 배열
    private val requiredPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //BLE Data 패킷을 받기 위한 권한 필요 (runtime 시 체크)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                requiredPermissions,
                PERMISSIONS_CODE
            )
        }

        macEt = findViewById(R.id.macEt)
        infoTv = findViewById(R.id.infoTv)

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if(bluetoothAdapter == null){
            //Device doesn't support Bluetooth
            finish()
        }

        leScanner = bluetoothAdapter.bluetoothLeScanner

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
                            //showMessage("$permission 권한 획득에 실패하였습니다.")
                            finish()
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        //블루투스 기능 on 여부 check off -> on으로 변경
        if(bluetoothAdapter?.isEnabled == false){
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            requestEnableActivity.launch(enableBtIntent)
        }
    }
    private val requestEnableActivity:ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){

    }

    override fun onPause() {
        super.onPause()
        leScanner.stopScan(scanCallback)
        if(bluetoothGatt!=null){
            bluetoothGatt.disconnect()
            bluetoothGatt.close()
        }
    }


    fun registerDev(view: View) {
        //editText에 저장된 맥 정보를 저장
        macStr = macEt.text.toString()
        showMessage("장비 등록 ${macStr}")
    }
    fun scanStart(view: View) {
        showMessage("scan start...")
        leScanner.startScan(scanCallback)
    }
    fun scanStop(view: View) {
        showMessage("scan stop...")
        leScanner.stopScan(scanCallback)
    }

    fun connectSensor(view: View) {
        showMessage("sensor connect 시작...")
        bluetoothGatt = bluetoothDevice.connectGatt(this, false, gattCallback)

    }

    //1. 스캔 관련 call-back
    val scanCallback = object  : ScanCallback(){
        //ble 장비로 부터 패킷을 받으면 아래 함수가 call-back
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            val remoteDev = result!!.device
            Log.e("INFO", "======================${remoteDev.toString()}====================")

            //연결하고자 하는 장비를 찾았을때...
            if(remoteDev.address == macStr){
                bluetoothDevice = remoteDev
                showMessage("해당 연결 센서${remoteDev.toString()} 스캔 완료...")
                leScanner.stopScan(this)
            }
        }
    }
    val gattCallback:BluetoothGattCallback = object : BluetoothGattCallback() {
        // 연결 상태가 변경되면 call-back
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            if(newState == BluetoothProfile.STATE_CONNECTED){
                Log.e("INFO","센서와 연결되었습니다.")
                showMessage("센서와 연결되었습니다...")
                //연결이 완료되면 센서에게 요청 : 가지고 있는 서비스 목록
                bluetoothGatt.discoverServices()

            }else if(newState == BluetoothProfile.STATE_DISCONNECTED){
                showMessage("센서와 연결이 끊겼습니다.")
            }
        }

        //#discoverServices() 호출하면 call-back 되는 함수...
        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
            showMessage("onServicesDiscoverd called.. 제공 서비스 확인함....")

            //센서 enable...

            //Notification 활성화...

        }

        //get하면 call-back...
        override fun onCharacteristicRead(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicRead(gatt, characteristic, status)
        }
        //set하면 call-back...
        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicWrite(gatt, characteristic, status)
        }
        //Notification을 수신하는 함수...
        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?
        ) {
            super.onCharacteristicChanged(gatt, characteristic)
        }
    }
    private fun showMessage(data:String){
        infoTv.append(data+"\n")
    }
}