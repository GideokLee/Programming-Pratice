package com.ssafy.comp_08.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.button_1).setOnClickListener{
            var intent = Intent(this, AutocompleteTextViewActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_2).setOnClickListener{
            var intent = Intent(this, MultiAutoCompleteTextViewActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_3).setOnClickListener{
            var intent = Intent(this, TextInputLayoutActivity::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.button_4).setOnClickListener{
            var intent = Intent(this, ScrollViewActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_5).setOnClickListener{
            var intent = Intent(this, HorizontalScrollViewActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_6).setOnClickListener{
            var intent = Intent(this, NestedScrollViewActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_8).setOnClickListener{
            var intent = Intent(this, SpinnerActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_9).setOnClickListener{
            var intent = Intent(this, FloatingActionButtonActivity::class.java)
            startActivity(intent)
        }
    }
}