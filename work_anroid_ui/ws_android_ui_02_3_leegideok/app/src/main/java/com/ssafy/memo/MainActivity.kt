package com.ssafy.memo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.ssafy.memo.util.Utils

private const val TAG = "MainActivity_싸피"
class MainActivity : AppCompatActivity() {

    private lateinit var todoListView : ListView
    private lateinit var btn_register : Button
    private lateinit var memoItemMgr : MemoItemMgr
    private lateinit var adapter: CustomListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_register = findViewById(R.id.btn_register)

        // 1. 추가
        memoItemMgr = MemoItemMgr()
        memoItemMgr.add(MemoItem("메모 앱 만들기1", "메모 앱 서로 연결하기", "03-29 03:19"))
        memoItemMgr.add(MemoItem("메모 앱 만들기2", "메모 앱 UI 만들기", "03-30 05:22"))
        memoItemMgr.add(MemoItem("메모 앱 만들기3", "메모 앱 설계하기", "03-31 10:30"))

        todoListView = findViewById(R.id.todoListView)
        adapter = CustomListAdapter(this, memoItemMgr.getList(), R.layout.list_item_layout)

        todoListView.adapter = adapter

        todoListView.setOnItemClickListener{ parent, view, position, id ->
            val intent = Intent(this, MemoEditActivity::class.java)
            intent.putExtra("memoItem", memoItemMgr.search(position))
            intent.putExtra("index", position)
            intent.putExtra("type","modify")
            memoEditActivityLauncher.launch(intent)

            adapter.notifyDataSetChanged()
            Log.d(TAG, memoItemMgr.search(position).title)
        }
        btn_register.setOnClickListener {
            val intent = Intent(this, MemoEditActivity::class.java)
            intent.putExtra("type","register")
            memoEditActivityLauncher.launch(intent)
        }

    }
    private val memoEditActivityLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode == Activity.RESULT_OK){
            val intent = it.data
            val type = intent!!.getStringExtra("type")
            val index = intent!!.getIntExtra("index", -1)
            if(type.equals("modify")){
                var item = intent.getSerializableExtra("item") as MemoItem
                memoItemMgr.update(index, item)
            }else if(type.equals("remove")){
                memoItemMgr.remove(index)
            }else{
                var item = intent.getSerializableExtra("item") as MemoItem
                memoItemMgr.add(item)
            }

            adapter.notifyDataSetChanged()
        }
    }

}
