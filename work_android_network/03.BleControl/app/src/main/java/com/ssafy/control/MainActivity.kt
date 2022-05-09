package com.ssafy.control

import android.Manifest
import android.app.Activity
import android.bluetooth.*
import android.bluetooth.BluetoothAdapter.LeScanCallback
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.*

class MainActivity : Activity() {

    private val LOGTAG = "BLECONTROL"
    private var blueMan: BluetoothManager? = null
    private var blueAdapter: BluetoothAdapter? = null
    private var gatt: BluetoothGatt? = null
    private var mTv: TextView? = null
    private var deviceList: ListView? = null
    private var mLeDeviceListAdapter: LeDeviceListAdapter? = null

    //new code ----
    private var leScnner: BluetoothLeScanner? = null


    //TI SensorTag UUID : TI사가 제공한 정보 ====================================
    var UUID_ACC_SERV = UUID
        .fromString("f000aa10-0451-4000-b000-000000000000")
    var UUID_ACC_DATA = UUID
        .fromString("f000aa11-0451-4000-b000-000000000000")
    var UUID_IRT_SERV = UUID
        .fromString("f000aa00-0451-4000-b000-000000000000")
    var UUID_IRT_DATA = UUID
        .fromString("f000aa01-0451-4000-b000-000000000000")
    var UUID_IRT_CONF = UUID
        .fromString("f000aa02-0451-4000-b000-000000000000")
    var UUID_KEY_SERV = UUID
        .fromString("0000ffe0-0000-1000-8000-00805f9b34fb")
    var UUID_KEY_DATA = UUID
        .fromString("0000ffe1-0000-1000-8000-00805f9b34fb")

    // ==================================================================
    // mServices.put("f000aa20-0451-4000-b000-000000000000",
    // "SensorTag Humidity Service");
    // mCharacteristics.put("f000aa21-0451-4000-b000-000000000000",
    // "SensorTag Humidity Data");
    // mCharacteristics.put("f000aa22-0451-4000-b000-000000000000",
    // "SensorTag Humidity Config");
    // mCharacteristics.put("f000aa23-0451-4000-b000-000000000000",
    // "SensorTag Humidity Period");
    
    var UUID_HUM_SERV = UUID
        .fromString("f000aa20-0451-4000-b000-000000000000")
    var UUID_HIM_DATA = UUID
        .fromString("f000aa21-0451-4000-b000-000000000000")
    var UUID_HIM_CONFIG = UUID
        .fromString("f000aa22-0451-4000-b000-000000000000")
    var UUID_GYR_SERV = UUID
        .fromString("f000aa50-0451-4000-b000-000000000000")
    var UUID_GYR_DATA = UUID
        .fromString("f000aa51-0451-4000-b000-000000000000")
    var UUID_GYR_CONFIG = UUID
        .fromString("f000aa52-0451-4000-b000-000000000000")


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

        mTv = findViewById<View>(R.id.mTv) as TextView
        blueMan = getSystemService(BLUETOOTH_SERVICE) as BluetoothManager
        blueAdapter = blueMan!!.adapter
        deviceList = findViewById<View>(R.id.devicelist) as ListView
        mLeDeviceListAdapter = LeDeviceListAdapter(this)
        deviceList!!.adapter = mLeDeviceListAdapter
        
        leScnner = blueAdapter!!.bluetoothLeScanner

        deviceList!!.onItemClickListener = OnItemClickListener { parent, view, position, id -> // TODO Auto-generated method stub
            val device = mLeDeviceListAdapter!!.getItem(position) as BluetoothDevice
            //해당 장비 정보 리스트에서 클릭시 해당 장비와 연결 시도
            gatt = device.connectGatt(
                applicationContext, false,
                gattCallback
            )
            h.sendEmptyMessage(CONNECTING)
        }
    }

    override fun onResume() {
        super.onResume()
        //블루투스 기능 비활성화시 활성화 요청
        if (blueAdapter?.isEnabled == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBtIntent, 0)
        }
    }

    //각종 센서의 이벤트를 받기 위한 메뉴
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_scan ->            // Toast.makeText(this, "Start Scan", Toast.LENGTH_SHORT).show();
                startScan()
            R.id.action_stop ->            // Toast.makeText(this, "Stop Scan", Toast.LENGTH_SHORT).show();
                stopScan()
            R.id.action_test -> {
                Toast.makeText(this, "test.... ", Toast.LENGTH_SHORT).show()
                //온도 테스트
//                activate_IRT()
                //자이로스코프 테스트
//                activate_GYR()
                //키 이벤트 테스트
                activate_KEY()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun startScan() {
        mLeDeviceListAdapter!!.clear()
        mLeDeviceListAdapter!!.notifyDataSetChanged()
        Log.e(LOGTAG, "startScan-----")

        // BLE Sensor Scan
        // Deprecated된 코드
        //blueAdapter!!.startLeScan(scanCallback)

        //new code ----
        leScnner!!.startScan(leScanCBack)
    }

    private fun stopScan() {
        Log.e(LOGTAG, "stopSCan-----")
        // Deprecated된 코드
        // BLE Sensor Scan Stop
        //blueAdapter!!.stopLeScan(scanCallback)

        //new code ----
        leScnner!!.stopScan(leScanCBack)

        if (gatt != null) {
            gatt!!.disconnect()
            gatt = null
        }
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
                            Log.e(LOGTAG, "$permission 권한 획득에 실패하였습니다.")
                            finish()
                        }
                    }
                }
            }
        }
    }


    // Deprecated된 코드
    var scanCallback = LeScanCallback { device, rssi, scanRecord -> // TODO Auto-generated method stub
        //Log.e("INFO", "scanCallBack.......")
        if (gatt == null) {
            val remoteDevice = device
            Log.e(LOGTAG, "================== Device ===================")
            Log.e(LOGTAG, remoteDevice.toString())
            //val remoteDevice1:BluetoothDevice = remoteDevice
            runOnUiThread {
                //변경될 수 있는(mutable/var) 타입을 캐스팅 할 경우 문제가 생긴다
                Log.e(LOGTAG, "mLeDiviceList ${mLeDeviceListAdapter}, remoteDevice : ${remoteDevice}")
                //새로 검색한 디바이스를 리스트에 추가하는 코드
                mLeDeviceListAdapter!!.addDevice(remoteDevice)
                //remoteDevice1?.let { mLeDeviceListAdapter!!.addDevice(it) }
                mLeDeviceListAdapter!!.notifyDataSetChanged()
            }
        }
    }

    //new code ----
    private  val leScanCBack = object : ScanCallback(){
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            if (gatt == null) {
                val remoteDevice = result!!.device
                Log.e(LOGTAG, "***********  Device ************* ")
                Log.e(LOGTAG, remoteDevice.toString())
                runOnUiThread {
                    Log.e(LOGTAG, "mLeDiviceList ${mLeDeviceListAdapter}," +
                            " remoteDevice : ${remoteDevice}")
                    //새로 검색된 장비 정보(Mac Address)를 리스트에 추가하는 코드
                    mLeDeviceListAdapter!!.addDevice(remoteDevice)
                    mLeDeviceListAdapter!!.notifyDataSetChanged()
                }
            }
        }
    }


    var gattCallback: BluetoothGattCallback = object : BluetoothGattCallback() {
        
        //Gatt Client와 Server간 연결 변경시 call-back 되는 함수
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            // TODO Auto-generated method stub
            super.onConnectionStateChange(gatt, status, newState)
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                h.sendEmptyMessage(CONNECTED)
                gatt.discoverServices()
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                h.sendEmptyMessage(DISCONNECT)
            }
        }

        //gatt.discoverServices() 호출시 call-back 되는 함수
        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            Log.i(LOGTAG, "====== onServicesDiscovered called......")
        }

        // gatt.readCharacteristic() 호출시(get 명령어)  call-back 되는 함수
        override fun onCharacteristicRead(gatt: BluetoothGatt, ch: BluetoothGattCharacteristic, status: Int) {
            Log.e(LOGTAG, "####### onCharacteristicRead")
            if (status == BluetoothGatt.GATT_SUCCESS) {
                getCharacteristic(ch)
            }
        }

        override fun onReadRemoteRssi(gatt: BluetoothGatt, rssi: Int, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.e(LOGTAG, "RSSI : $rssi")
                // h.obtainMessage(RSSI, rssi).sendToTarget();
            }
        }

        //Notification 수신시 call-back 되는 함수
        override fun onCharacteristicChanged(gatt: BluetoothGatt, ch: BluetoothGattCharacteristic) {
            Log.i(LOGTAG, "onCharacteristicChanged========================")
            if (gatt != null) {
                getCharacteristic(ch)
            }
        }

        //Gatt Server에 set 명령어 수행시 호출되는 함수 (설정변경 등...)
        override fun onCharacteristicWrite(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int) {
            Log.e(LOGTAG, "onCharacteristicWrite****************************")
        }

        //Descriptor 설정 변경시 호출되는 함수
        override fun onDescriptorWrite(gatt: BluetoothGatt, descriptor: BluetoothGattDescriptor, status: Int) {
            Log.e(LOGTAG, "onDescriptorWrite------------------------")
            Log.e(
                LOGTAG, "descriptor : " + descriptor.uuid
                        + ", status : " + status
            )
        }
    }
    var value: String? = null
    //전달된 Characteristic 값 변환하는 함수
    fun getCharacteristic(ch: BluetoothGattCharacteristic?) {
        if (blueAdapter == null || gatt == null || ch == null) {
            return
        }
        val uuid = ch.uuid
        val rawValue = ch.value
        value = Conversion.BytetohexString(rawValue, rawValue.size)
        runOnUiThread { // TODO Auto-generated method stub
            mTv!!.text = value
        }
        if (rawValue.size > 0) {
            Log.e(LOGTAG, "&&&&&&&&&&&&&&&&&&&&&&&&&&&")
            Log.e(value, "value : $value")
            Log.e(LOGTAG, "&&&&&&&&&&&&&&&&&&&&&&&&&&&")
        }
    }

    //List<BluetoothGattService> services;
    private val CONNECTING = 1
    private val CONNECTED = 2
    private val DISCONNECT = 3
    private val RSSI = 4
    private val MSG = 5

    
    var h = Handler { msg ->
        when (msg.what) {
            CONNECTING -> Toast.makeText(
                applicationContext, "CONNECTING",
                Toast.LENGTH_SHORT
            ).show()
            CONNECTED -> {
                Toast.makeText(
                    applicationContext, "CONNECTED",
                    Toast.LENGTH_SHORT
                ).show()
                (findViewById<View>(R.id.mTv) as TextView).text = "BLESensor 에 연결되었습니다."
            }
            DISCONNECT -> {
                Toast.makeText(
                    applicationContext, "DISCONNECT",
                    Toast.LENGTH_SHORT
                ).show()
                (findViewById<View>(R.id.mTv) as TextView).text = "BLESensor 에 연결이 끈어졌습니다."
            }
            RSSI -> Toast.makeText(
                applicationContext, "rssi : " + msg.obj,
                Toast.LENGTH_SHORT
            ).show()
            MSG -> Toast.makeText(
                applicationContext, "info : " + msg.obj,
                Toast.LENGTH_SHORT
            ).show()
            else -> {
            }
        }
        true
    }


    override fun onPause() {
        // TODO Auto-generated method stub
        super.onPause()
        stopScan()
    }

    fun sleep(time: Int) {
        try {
            Thread.sleep(time.toLong())
        } catch (e: InterruptedException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }


    //센서 데이터를 가져오기 위해 해당 센서 기능을 활성화 시키는 함수
    private fun enableSensor(sevUuid: UUID, confUuid: UUID) {
        Log.e(LOGTAG, "enableSensor 수행 시작 ------------------")
        val charistic = gatt!!.getService(sevUuid)
            .getCharacteristic(confUuid)
        val value: Byte = 1
        val `val` = ByteArray(1)
        `val`[0] = value
        charistic.value = `val`
        //센서에 set -> 센싱 기능을 활성화...
        gatt!!.writeCharacteristic(charistic)
        Log.e(LOGTAG, "enableSensor 수행 완료 ------------------")
        try {
            Thread.sleep(100)
        } catch (e: InterruptedException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    //온도 정보 Noti로 받기 위한 코드
    private fun activate_IRT() {
        //센서 기능 활상화... -> 센싱 start...
        enableSensor(UUID_IRT_SERV, UUID_IRT_CONF)

        //센서의 값이 변경되면.. 정해진 주기에 맞게 데이터 전송...
        val c = gatt!!.getService(UUID_IRT_SERV).getCharacteristic(UUID_IRT_DATA)
        //Notification 기능 활성화 ~~~
        gatt!!.setCharacteristicNotification(c, true)
        //CCCD 값 off -> on
        val descriptor = c.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb")
        )
        if (descriptor != null) {
            val `val` = if (true) BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE else
                BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE
            descriptor.value = `val`
            //sensor로 Noti On~~~
            gatt!!.writeDescriptor(descriptor)
        }
        if (c.value != null) {
            Log.d(LOGTAG, "testButton1 501 : " + c.value[0])
        }
        try {
            Thread.sleep(100)
        } catch (e: InterruptedException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    //자이로스코프 정보 Noti로 받기 위한 코드
    private fun activate_GYR() {
        enableSensor(UUID_GYR_SERV, UUID_GYR_CONFIG)
        val c = gatt!!.getService(UUID_GYR_SERV).getCharacteristic(UUID_GYR_DATA)
        gatt!!.setCharacteristicNotification(c, true)
        val descriptor = c.getDescriptor(
            UUID
                .fromString("00002902-0000-1000-8000-00805f9b34fb")
        )
        if (descriptor != null) {
            val `val` = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            descriptor.value = `val`
            gatt!!.writeDescriptor(descriptor)
        }
        if (c.value != null) {
            Log.d(LOGTAG, "testButton1 501 : " + c.value[0])
        }
        try {
            Thread.sleep(100)
        } catch (e: InterruptedException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    // KEY_DATA 를 Noti로 받기 위한 코드
    private fun activate_KEY() {
        var c: BluetoothGattCharacteristic? = null
        c = gatt!!.getService(UUID_KEY_SERV).getCharacteristic(UUID_KEY_DATA)
        gatt!!.setCharacteristicNotification(c, true)
        val descriptor = c.getDescriptor(
            UUID
                .fromString("00002902-0000-1000-8000-00805f9b34fb")
        )
        if (descriptor != null) {
            val `val` = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            descriptor.value = `val`
            gatt!!.writeDescriptor(descriptor)
        }
    }
}