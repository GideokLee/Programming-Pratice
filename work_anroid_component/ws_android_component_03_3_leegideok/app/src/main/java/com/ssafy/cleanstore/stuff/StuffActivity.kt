package com.ssafy.cleanstore.stuff

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.ssafy.cleanstore.R
import com.ssafy.cleanstore.dto.Stuff
import com.ssafy.cleanstore.service.BoundService
import com.ssafy.cleanstore.service.MyBinder
import kotlin.math.log

enum class ActionFlag {
    REGISTER, DELETE, MODIFY
}

private const val TAG = "StuffActivity_싸피"
class StuffActivity : AppCompatActivity() {

    private lateinit var btnRegister: Button
    private lateinit var stuffList: ArrayList<Stuff>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stuff)

        btnRegister = findViewById(R.id.btn_stuff_register)

        // ListView에 들어갈 ArrayList 생성
        stuffList = arrayListOf()

        // Adapter 생성
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stuffList)

        // ListView 연결
        val listView: ListView = findViewById(R.id.listview_stuff_stuff)

        // Adapter와 ListView 연결
        listView.adapter = adapter

        // Intent 사용
        val stuffEditLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult() // StartActivityForResult 처리를 담당
        ) { activityResult -> // 넘어갔다 돌아오는 Result 값을 받는 인자
            if (activityResult.resultCode == Activity.RESULT_OK) {  // 잘 넘어온지 확인

                val intent = activityResult.data
                val actionFlag = intent!!.getSerializableExtra("OActionFlag")
                val position = intent.getIntExtra("OPosition", -1)

                when (actionFlag) {
                    ActionFlag.DELETE -> {
                        stuffList.removeAt(position)
                    }

                    ActionFlag.REGISTER -> {
                        val name = intent.getStringExtra("OName")
                        val count = intent.getIntExtra("OCount", -1)
                        MyServiceConnection.myService.stuffInsert(Stuff(1,name!!,count))
                        Toast.makeText(this, MyServiceConnection.myService.selectAll()[1].toString(),Toast.LENGTH_SHORT);
                        stuffList.add(Stuff(name, count))
                    }

                    ActionFlag.MODIFY -> {
                        stuffList[position].apply {
                            val name = intent.getStringExtra("OName")
                            val count = intent.getIntExtra("OCount", -1)
                            this.name = name
                            this.count = count
                        }
                    }
                }

                adapter.notifyDataSetChanged()
            }
        }
        // 이동위한 Intent 생성
        Intent(this, StuffEditActivity::class.java).apply {
            // 물품 등록
            btnRegister.setOnClickListener {
                // 값의 초기화
                putExtra("Name", "")
                putExtra("Count", -1)
                putExtra("ActionFlag", ActionFlag.REGISTER)
                stuffEditLauncher.launch(this)

                if(MyServiceConnection.isBound){
                }
            }
            // 물품 수정
            listView.setOnItemClickListener { parent, view, position, id ->
                val stuff = stuffList[position]
                // 원래 값 넘겨주기
                putExtra("Name", stuff.name)
                putExtra("Count", stuff.count)
                putExtra("Position", position)
                putExtra("ActionFlag", ActionFlag.MODIFY)
                stuffEditLauncher.launch(this)
            }
        }


    }
    override fun onStart() {
        super.onStart()

        if(!MyServiceConnection.isBound){
            Intent(this, BoundService::class.java).also{
                bindService(it, MyServiceConnection, Context.BIND_AUTO_CREATE)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if(MyServiceConnection.isBound){
            unbindService(MyServiceConnection)
            MyServiceConnection.isBound = false
        }
    }

}

object  MyServiceConnection : ServiceConnection {

    lateinit var myService : BoundService

    var isBound = false

    override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
        val binder = p1 as MyBinder
        myService = binder.getService()
        isBound = true
    }

    override fun onServiceDisconnected(p0: ComponentName?) {
        isBound = false
    }
}