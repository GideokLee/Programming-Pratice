package com.ssafy.ssafygo.storeMenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ssafy.ssafygo.R
import com.ssafy.ssafygo.dto.StoreMenuDTO

class StoreMenuDetailActivity : AppCompatActivity() {
    private lateinit var tv_name : TextView
    private lateinit var tv_price : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_menu_detail)

        tv_name = findViewById(R.id.tv_menu_name)
        tv_price = findViewById(R.id.tv_menu_price)

        val storeMenu = intent.getSerializableExtra("StoreMenu") as StoreMenuDTO

        tv_name.setText(storeMenu.name)
        tv_price.setText(storeMenu.price.toString())
    }
}