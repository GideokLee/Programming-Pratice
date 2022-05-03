package com.ssafy.ssafygo.storeMenu

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
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

class StoreMenuDetailActivity : AppCompatActivity() {
    private lateinit var tv_name : TextView
    private lateinit var tv_price : TextView
    private lateinit var btn_order : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_menu_detail)

        tv_name = findViewById(R.id.tv_menu_name)
        tv_price = findViewById(R.id.tv_menu_price)

        val storeMenu = intent.getSerializableExtra("StoreMenu") as StoreMenuDTO

        tv_name.setText(storeMenu.name)
        tv_price.setText(storeMenu.price.toString())

        btn_order = findViewById(R.id.btn_order)

        btn_order.setOnClickListener {
            createDialog(storeMenu)
        }
    }

    fun createDialog(menu : StoreMenuDTO){
        AlertDialog.Builder(this)
            .setTitle("매뉴 주문")
            .setMessage("주문하실 매뉴가 '${menu.name}' 맞습니까?")
            .setPositiveButton("예", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                    val date = Utils.formatter().format(Date())
                    var receipt = ReceiptDTO(menu.name, menu.price, date)
                    setReceipt(receipt)
                }
            })
            .setNegativeButton("아니요", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, which: Int) {
                }
            })
            .create()
            .show()
    }

    private fun setReceipt(receipt : ReceiptDTO){
        val receiptService = ApplicationClass.retrofit.create(ReceiptService::class.java)
        receiptService.setReceipt(receipt).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val res = response.body()
                if(response.code() == 200){
                    Toast.makeText(this@StoreMenuDetailActivity,
                    "주문이 등록 되었습니다.",
                    Toast.LENGTH_SHORT).show()
               }else{
                    Toast.makeText(this@StoreMenuDetailActivity,
                        "주문 등록 실패",
                        Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}