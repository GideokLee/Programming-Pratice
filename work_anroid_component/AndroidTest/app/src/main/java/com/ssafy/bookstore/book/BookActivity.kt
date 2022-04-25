package com.ssafy.bookstore.book

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.ssafy.bookstore.BoundServiceConnection
import com.ssafy.bookstore.R
import com.ssafy.bookstore.dto.Book
import com.ssafy.bookstore.service.BoundService
import com.ssafy.memo.util.Utils

enum class ActionFlag {
    REGISTER, DELETE, MODIFY
}

class BookActivity : AppCompatActivity() {

    private lateinit var btnRegister: Button
    private lateinit var bookList: MutableList<Book>
    private lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        btnRegister = findViewById(R.id.btn_register)

        listView = findViewById(R.id.listview_book)

        updateListView()

        val  BookEditLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){activityResult ->
            if(activityResult.resultCode == Activity.RESULT_OK){

            }
        }

        Intent(this, BookEditActivity::class.java).apply {
            btnRegister.setOnClickListener {
                putExtra("book", Book("","","",0,
                    Utils.formatter().format(System.currentTimeMillis()).toString()))
                putExtra("ActionFlag", ActionFlag.REGISTER)
                BookEditLauncher.launch(this)
            }

            listView.setOnItemClickListener { parent, view, position, id ->
                val book = bookList[position]

                putExtra("book", Book(book.title,book.author,book.content,book.price,book.date))
                putExtra("Position", position)
                putExtra("ActionFlag", ActionFlag.MODIFY)
                BookEditLauncher.launch(this)
            }
        }
    }
    private fun updateListView(){
        bookList = BoundServiceConnection.myService.bookSelectAll()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, bookList)

        listView.adapter = adapter

        adapter.notifyDataSetChanged()
    }
}