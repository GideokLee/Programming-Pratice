package com.ssafy.cleanstore.stuff

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.ssafy.cleanstore.R
import com.ssafy.cleanstore.dto.Stuff

class StuffActivity : AppCompatActivity() {
    private lateinit var listView : ListView
    private lateinit var stuffList : MutableList<Stuff>
    private lateinit var btn_register : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stuff)

        stuffList = mutableListOf()
        stuffList.add(Stuff("Stuff1",1))
        stuffList.add(Stuff("Stuff2",2))
        stuffList.add(Stuff("Stuff3",3))

        listView = findViewById(R.id.listView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stuffList)

        listView.adapter = adapter

        val intent = Intent(this, StuffEditActivity::class.java)

        listView.setOnItemClickListener{parent, view, position, id ->
            intent.putExtra("state",1)
            intent.putExtra("name", stuffList[position].name)
            intent.putExtra("volume", stuffList[position].volume)
            intent.putExtra("index", position)
            stuffEditActivityLauncher.launch(intent)
        }

        btn_register = findViewById(R.id.btn_register)

        btn_register.setOnClickListener {
            intent.putExtra("state",0)
            intent.putExtra("name","")
            intent.putExtra("volume",-1)
            intent.putExtra("index", -1)
            stuffEditActivityLauncher.launch(intent)
        }
    }

    private val stuffEditActivityLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            val intent : Intent? = it.data

            val state = intent!!.getIntExtra("state",-1)
            val name = intent!!.getStringExtra("name")
            val volume = intent!!.getIntExtra("volume", -1)
            val index = intent!!.getIntExtra("index", -1)
            val s = Stuff(name!!,volume)

            when(state){
                0 -> stuffList.add(s)
                1 -> stuffList[index] = s
                2 -> stuffList.removeAt(index)
            }
            updateListView()
        }
    }

    private fun updateListView() {
        // Adapter 생성
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, stuffList)

        // ListView와 Adapter 연결
        listView.adapter = adapter

        // ListView 변경 적용
        adapter.notifyDataSetChanged()
    }
}