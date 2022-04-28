package com.ssafy.ssafygo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ssafy.ssafygo.dao.StoreDAO
import com.ssafy.ssafygo.dto.StoreDTO
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val PROGRESS_CNT = 10
    private val PROGRESS_TICK = 300
    private lateinit var storeDAO: StoreDAO
    private val random : Random = Random.Default
    private lateinit var sendBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendBtn = findViewById(R.id.btn_load)
        sendBtn.setOnClickListener {

        }
    }
    private fun getStoreInfo() : StoreDTO? {
        return storeDAO.storeSelect(1)
    }
    private fun initDAO(){
        storeDAO.dbOpenHelper(this)
        storeDAO.open()
    }

    private fun loadStore(){
        for(i in PROGRESS_CNT downTo 1){
        }
    }
    private fun setStoreTV(){

    }
    private fun rand(end: Int, start: Int): Int{
        return random.nextInt(start - end) + end
    }


}