package com.ssafy.ssafygo.storeMenu

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.ssafy.ssafygo.ApplicationClass
import com.ssafy.ssafygo.R
import com.ssafy.ssafygo.dto.StoreMenuDTO
import com.ssafy.ssafygo.dto.StoreReviewDTO
import com.ssafy.ssafygo.receipt.ReceiptListActivity
import com.ssafy.ssafygo.service.StoreMenuService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreMenuActivity : AppCompatActivity() {

    private var storeMenuList: ArrayList<StoreMenuDTO> = arrayListOf()
    private lateinit var adapter: ArrayAdapter<StoreMenuDTO>
    private lateinit var btn_order_list : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_menu)

        val listView : ListView = findViewById(R.id.listview_store_menu)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, storeMenuList)
        listView.adapter = adapter
        btn_order_list = findViewById(R.id.btn_order_list)
        btn_order_list.setOnClickListener {
            val intent = Intent(this, ReceiptListActivity::class.java)
            startActivity(intent)
        }
        listView.setOnItemClickListener{ parent, view, position, id ->
            val storeMenu = storeMenuList[position]
            val intent = Intent(this, StoreMenuDetailActivity::class.java)
            intent.putExtra("StoreMenu", storeMenu)
            startActivity(intent)
        }
        getNdefMessages(intent)
    }

    private fun getNdefMessages(intent: Intent) : Array<NdefMessage?>? {
        var msgs : Array<NdefMessage?>? = null
        var rawMsg = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)

        if(rawMsg != null){
            msgs = arrayOfNulls<NdefMessage>(rawMsg.size)

            for(i in rawMsg.indices){
                msgs[i] = rawMsg[i] as NdefMessage?
            }
            val recInfo = msgs[0]!!.records[0]
            val data = recInfo.type
            val recType = String(data)

            if(recType == "T"){
                val storeId: String = String(recInfo.payload, 3, recInfo.payload.size - 3)
                getStoreMenuInfo(storeId.toInt())
            }
        }
        return msgs
    }

    private fun getStoreMenuInfo(storeId: Int){
        val storeService = ApplicationClass.retrofit.create(StoreMenuService::class.java)

        storeService.getStoreMenus(storeId).enqueue(object : Callback<List<StoreMenuDTO>> {
            override fun onResponse(call: Call<List<StoreMenuDTO>>, response: Response<List<StoreMenuDTO>>) {
                val res = response.body()
                if(response.code() == 200){
                    storeMenuList.clear()
                    if(res != null){
                        storeMenuList.addAll(res)
                    }else{
                        Toast.makeText(this@StoreMenuActivity,
                        "매뉴 정보를 가져올 수 없습니다.",
                        Toast.LENGTH_SHORT).show()
                    }
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<StoreMenuDTO>>, t: Throwable) {
                Log.d("StoreMenuActivity", t.message?: "매뉴 정보 불로오는 중 통신오류")
            }
        })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }
}