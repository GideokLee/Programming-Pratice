package com.ssafy.bookstore.book

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ssafy.bookstore.R
import com.ssafy.bookstore.databinding.ActivityBookEditBinding
import com.ssafy.bookstore.dto.Book

class BookEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityBookEditBinding.inflate(layoutInflater).apply {
            setContentView(root)

            val book = intent.getSerializableExtra("book") as Book
            val position = intent.getIntExtra("Position", -1)
            val actionFlag = intent.getSerializableExtra("ActionFlag")

            when(actionFlag){
                ActionFlag.REGISTER ->{
                    btnBookEditDelete.visibility = View.GONE
                }
                ActionFlag.MODIFY ->{
                    btnBookEditDelete.visibility = View.VISIBLE

                    etBookEditBookTitle.setText(book.title)
                    etBookEditBookAuthor.setText(book.author)
                    etBookEditBookContent.setText(book.content)
                    etBookEditBookPrice.setText(book.price)
                }
            }
            btnBookEditCancel.setOnClickListener {
                finish()
            }
        }
    }
}