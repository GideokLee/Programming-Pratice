package com.ssafy.ssafygo.receipt

import android.app.PendingIntent
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.Ndef
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ssafy.gallery.util.Utils
import com.ssafy.ssafygo.ApplicationClass
import com.ssafy.ssafygo.R
import com.ssafy.ssafygo.dto.ReceiptDTO
import com.ssafy.ssafygo.dto.StoreMenuDTO
import com.ssafy.ssafygo.service.ReceiptService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class ReceiptListActivity : AppCompatActivity() {
    private var receiptList: ArrayList<ReceiptDTO> = arrayListOf()
    private lateinit var adapter: ArrayAdapter<ReceiptDTO>
    private lateinit var nfcAdapter: NfcAdapter
    private lateinit var pIntent: PendingIntent
    private lateinit var filters:Array<IntentFilter>
    private lateinit var btn_write:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_list)

        val listView :ListView = findViewById(R.id.listview_store_receipt)

        btn_write = findViewById(R.id.btn_receipt_register)
        btn_write.setOnClickListener {
            val ad = createDialog()
            ad.show()
            val action = intent!!.action
            if(action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)||
                action.equals(NfcAdapter.ACTION_TECH_DISCOVERED)||
                action.equals(NfcAdapter.ACTION_TAG_DISCOVERED)){
                val detectTag = intent!!.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
                writeTag(makeNedfMsg(), detectTag!!)
            }
            //ad.dismiss()
        }
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, receiptList)
        listView.adapter = adapter
        getReceiptAll()

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        if(nfcAdapter==null){
            finish()
        }
        val intent = Intent(this, ReceiptListActivity::class.java).apply {
            setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        }
        pIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val tag_filter = IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        filters = arrayOf(tag_filter)

    }
    private fun getReceiptAll(){
        val receiptService = ApplicationClass.retrofit.create(ReceiptService::class.java)

        receiptService.getReceiptAll().enqueue(object : Callback<List<ReceiptDTO>>{
            override fun onResponse(
                call: Call<List<ReceiptDTO>>,
                response: Response<List<ReceiptDTO>>
            ) {
                val res = response.body()
                if(response.code() == 200){
                    receiptList.clear()
                    if(res != null){
                        receiptList.addAll(res)
                    }else{
                        Toast.makeText(this@ReceiptListActivity,
                            "주문 내역을 가져올 수 없습니다.",
                            Toast.LENGTH_SHORT).show()
                    }
                    adapter.notifyDataSetChanged()
                }else{

                }
            }
            override fun onFailure(call: Call<List<ReceiptDTO>>, t: Throwable) {
            }

        })
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }

    fun createDialog() : AlertDialog{
        val ad = AlertDialog.Builder(this)
            .setTitle("마지막 주문내용 저장")
            .setMessage("주문내용을 저장할 NFC를 태깅해주세요.")
            .setNegativeButton("아니요", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                }
            })
            .create()
        return ad
    }

    private fun makeNedfMsg(): NdefMessage {

        var nedfM: NdefMessage? = null
        var nedfR: NdefRecord? = null

        nedfR = NdefRecord.createTextRecord("ko", receiptList[0].toString())

        return NdefMessage(arrayOf(nedfR))
    }

    private fun writeTag(msg:NdefMessage, tag: Tag){

        //ndef 객체를 얻는다 : Ndef.get(tag)
        val ndef = Ndef.get(tag)
        val msgSize = msg.toByteArray().size
        //ndef 객체를 이용해서 connect

        if(ndef!=null){
            ndef.connect()

            if(!ndef.isWritable){
                return
            }
            if(ndef.maxSize < msgSize){
                return
            }
            Toast.makeText(this,"태그에 데이터를 write 합니다..",Toast.LENGTH_SHORT).show()
            ndef.writeNdefMessage(msg)
        }

        //ndef 객체의 writeNdefMessage(msg) 태그에 write 한다..

    }

    override fun onPause() {
        super.onPause()
        nfcAdapter.disableForegroundDispatch(this)
    }

    override fun onResume() {
        super.onResume()
        nfcAdapter.enableForegroundDispatch(this,pIntent,filters,null)
    }

}