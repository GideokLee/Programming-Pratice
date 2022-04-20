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
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ssafy.cleanstore.BoundServiceConnention
import com.ssafy.cleanstore.R
import com.ssafy.cleanstore.dao.StuffDao
import com.ssafy.cleanstore.databinding.ActivityStuffBinding
import com.ssafy.cleanstore.dto.Stuff
import com.ssafy.cleanstore.service.BoundService
import java.util.ArrayList

class StuffActivity : AppCompatActivity() {

    private val REGISTER = 0
    private val DELETE = 1
    private val MODIFY = 2

    private var isBound = false

    private val stuffDAO = StuffDao()

    // ListView에 들어갈 String List생성
    private var stuffList : MutableList<Stuff> = arrayListOf()

    // Intent 사용
    private val requestActivity: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == Activity.RESULT_OK) {
            val intent : Intent? = activityResult.data
            val stuff = intent?.getSerializableExtra("stuff") as Stuff

            when(intent.getIntExtra("OActionFlag", 0)){
                REGISTER -> {
                    BoundServiceConnention.boundService.stuffInsert(stuff)
                }
                MODIFY -> {
                    BoundServiceConnention.boundService.stuffUpdate(stuff)
                }
                DELETE -> {
                    BoundServiceConnention.boundService.stuffDelete(stuff.id)
                }
            }
            initAdapter()
        }
    }

    private fun initDatabase() {
        //helper 인스턴스 생성 -> helper() 생성자가 실행되어 memos 파일이 생성된다.
        stuffDAO.dbOpenHelper(this)
        stuffDAO.open()
        stuffDAO.create()
    }

    private fun initAdapter(){
        // Stuff 데이터베이스가 바뀌어서 한번 지워야함
        stuffList = stuffDAO.stuffSelectAll()

        // Adapter 생성
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stuffList)
        // ListView 연결
        val listview: ListView = findViewById(R.id.listview_stuff_stuff)

        // 물품 수정 이벤트
        listview.setOnItemClickListener { parent, view, position, id ->
            val stuff = stuffList[position]
            // 원래 값 넘겨주기
            requestActivity.launch(Intent(applicationContext, StuffEditActivity::class.java).apply {
                putExtra("stuff", stuff)
                putExtra("ActionFlag", MODIFY)
            })
        }

        // Adapter와 ListView 연결
        listview.adapter = adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stuff)

        // DB 초기 설정
        initDatabase()

        // Stuff 리스트 가져오기
        initAdapter()

        // 생성 버튼 연결
        val btnRegister = findViewById<FloatingActionButton>(R.id.btn_stuff_register)

        // 물품 등록
        btnRegister.setOnClickListener {
            requestActivity.launch(Intent(applicationContext, StuffEditActivity::class.java).apply {
                putExtra("stuff", Stuff(-1, "", -1, ""))
                putExtra("ActionFlag", REGISTER)
            })
        }
    }
}