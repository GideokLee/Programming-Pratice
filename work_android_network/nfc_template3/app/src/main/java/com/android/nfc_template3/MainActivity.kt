package com.android.nfc_template3

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var infoTv :TextView
    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var pIntent: PendingIntent
    private lateinit var filters:Array<IntentFilter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        infoTv= findViewById(R.id.infoTv)
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if(nfcAdapter==null){
            finish()
        }

        val intent = Intent(this, javaClass).apply {
            setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }

        pIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val ndef_filter = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        ndef_filter.addDataType("text/plain")
        val ndef_filter1 = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        ndef_filter1.addDataScheme("https")
        val ndef_filter2 = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        ndef_filter2.addDataScheme("http")

        filters = arrayOf(ndef_filter,ndef_filter1,ndef_filter2)


    }

    override fun onResume() {
        super.onResume()
        nfcAdapter.enableForegroundDispatch(this,pIntent,filters,null)
    }

    override fun onPause() {
        super.onPause()
        nfcAdapter.disableForegroundDispatch(this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.e("Info", "onNewIntent called...")
        val action = intent!!.action
        if(action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)){
            processIntent(intent)
        }
    }

    fun processIntent(intent:Intent){
        val rawMsg = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)

        if(rawMsg != null) {
            val msgArr = arrayOfNulls<NdefMessage>(rawMsg.size)

            for (i in rawMsg.indices) {
                msgArr[i] = rawMsg[i] as NdefMessage?
            }
            val recInfo = msgArr[0]!!.records[0]
            val data = recInfo.type
            val recType = String(data)
//            val payload = msgArr[0]!!.records[0].payload
//            tv.setText("태그 데이터 : ${String(payload, 3, payload.size -3 )}")
//            tv.setText("수신된 태그의 타입 : ${String(recType)}")

            if (recType == "T") {
                infoTv.setText("태그 데이터 :  ${String(recInfo.payload, 3, recInfo.payload.size - 3)}")
            } else if (recType == "U") {
                val uriInfo = recInfo.toUri().toString()
                infoTv.setText("URI tag data : $uriInfo")
            }
        }
    }

}
