package com.ssafy.memo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


private const val TAG = "MainActivity_싸피"
class MainActivity : AppCompatActivity() {

    private lateinit var todoListView : ListView
    private lateinit var memoItemMgr : MemoItemMgr
    private lateinit var adapter: CustomListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 추가
        memoItemMgr = MemoItemMgr()
        memoItemMgr.add(MemoItem("메모 앱 만들기1", "메모 앱 서로 연결하기", "03-29 03:19"))
        memoItemMgr.add(MemoItem("메모 앱 만들기2", "메모 앱 UI 만들기", "03-30 05:22"))
        memoItemMgr.add(MemoItem("메모 앱 만들기3", "메모 앱 설계하기", "03-31 10:30"))

        todoListView = findViewById(R.id.todoListView)
        adapter = CustomListAdapter(this, memoItemMgr.getList(), R.layout.list_item_layout)

        todoListView.adapter = adapter
        registerForContextMenu(todoListView)

        todoListView.setOnItemClickListener{ parent, view, position, id ->
            val intent = Intent(this, MemoEditActivity::class.java)
            intent.putExtra("memoItem", memoItemMgr.search(position))
            intent.putExtra("index", position)
            intent.putExtra("type","modify")
            memoEditActivityLauncher.launch(intent)
            adapter.notifyDataSetChanged()
            Log.d(TAG, memoItemMgr.search(position).title)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_context, menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info : AdapterView.AdapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        when(item.itemId){
            R.id.context_menu_delete ->{
                memoItemMgr.remove(info.position)
                adapter.notifyDataSetChanged()
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, MemoEditActivity::class.java)
        intent.putExtra("type","register")
        memoEditActivityLauncher.launch(intent)
        return super.onOptionsItemSelected(item)
    }
    private val memoEditActivityLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode == Activity.RESULT_OK){
            val intent = it.data
            val type = intent!!.getStringExtra("type")
            val index = intent!!.getIntExtra("index", -1)
            if(type.equals("modify")) {
                var item = intent.getSerializableExtra("item") as MemoItem
                memoItemMgr.update(index, item)
            }
            else{
                var item = intent.getSerializableExtra("item") as MemoItem
                memoItemMgr.add(item)
            }
            adapter.notifyDataSetChanged()
        }
    }

}
