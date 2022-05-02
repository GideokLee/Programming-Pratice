package com.android.nfc_template

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = findViewById(R.id.infoTv)

        var recI = intent
        var action = recI.action

        tv.setText("수신 액션 :$action")

        processIntent(recI)

    }


    private fun processIntent(intent: Intent){
        val rawMsg = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)

        if(rawMsg != null){
            val msgArr = arrayOfNulls<NdefMessage>(rawMsg.size)

            for(i in rawMsg.indices){
                msgArr[i] = rawMsg[i] as NdefMessage?
            }
            val recInfo = msgArr[0]!!.records[0]
            val data = recInfo.type
            val recType = String(data)
//            val payload = msgArr[0]!!.records[0].payload
//            tv.setText("태그 데이터 : ${String(payload, 3, payload.size -3 )}")
//            tv.setText("수신된 태그의 타입 : ${String(recType)}")

            if(recType == "T"){
                tv.setText("태그 데이터 :  ${String(recInfo.payload, 3, recInfo.payload.size-3)}")
            }else if(recType == "U"){
                val uriInfo = recInfo.toUri().toString()
                tv.setText("URI tag data : $uriInfo")

            }
        }
    }
}