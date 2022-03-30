package com.ssafy.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class ButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        val btn1: Button = findViewById(R.id.button1)

        btn1.setOnClickListener { v: View? ->
            Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show()
        }

        btn1.setOnClickListener { v ->
            Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show()
        }

        btn1.setOnClickListener {
            Toast.makeText(this, "Hello World", Toast.LENGTH_SHORT).show()
        }

        btn1.setOnClickListener {
            Toast.makeText(this, "Hello World" + it.javaClass.name, Toast.LENGTH_SHORT).show()
        }
    }
}