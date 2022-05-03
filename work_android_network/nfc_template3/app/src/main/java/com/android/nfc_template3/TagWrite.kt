package com.android.nfc_template3

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TagWrite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tag_write)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val detectTag = intent!!.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)

        //writeTag 함수 호출

    }

    //T, "ssafy" / U, "https://www.daum.net"
    private fun makeNedfMsg(type:String, data:String): NdefMessage {

        var nedfM:NdefMessage? = null
        var nedfR:NdefRecord? = null

        if(type.equals("T")){
            nedfR = NdefRecord.createTextRecord("en", data)
        }else if(type.equals("U")){
            nedfR = NdefRecord.createUri(data)
        }else{
            //모르는 형태...
        }

        return NdefMessage(arrayOf(nedfR))
    }

    private fun writeTag(msg:NdefMessage, tag: Tag){

        //ndef 객체를 얻는다 : Ndef.get(tag)

        //ndef 객체를 이용해서 connect

        //ndef 객체의 writeNdefMessage(msg) 태그에 write 한다..


    }


}