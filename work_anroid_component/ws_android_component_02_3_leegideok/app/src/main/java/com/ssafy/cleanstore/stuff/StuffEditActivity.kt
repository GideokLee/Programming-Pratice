package com.ssafy.cleanstore.stuff

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.ssafy.cleanstore.R

class StuffEditActivity : AppCompatActivity() {
    private lateinit var edit_name :EditText
    private lateinit var edit_volume :EditText

    private lateinit var btn_register : Button
    private lateinit var btn_cancel : Button
    private lateinit var btn_delete : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stuff_edit)

        edit_name = findViewById(R.id.name)
        edit_volume = findViewById(R.id.volume)

        btn_register = findViewById(R.id.btn_register)
        btn_delete = findViewById(R.id.btn_delete)
        btn_cancel = findViewById(R.id.btn_cancel)
        val index = intent.getIntExtra("index",-1)
        val state = intent.getIntExtra("state", -1)
        if(state == 0) btn_delete.visibility = View.GONE
        else{
            edit_name.setText(intent.getStringExtra("name"))
            edit_volume.setText(intent.getIntExtra("volume", 0).toString())
        }

        val intent = Intent(this,StuffActivity::class.java)

        btn_register.setOnClickListener{
            if(edit_name.text.isEmpty() || edit_volume.text.isEmpty()){
                Toast.makeText(this, "모든 빈칸을 채워주세요", Toast.LENGTH_SHORT).show()
            }else{
                val volume = Integer.parseInt(edit_volume.text.toString())
                intent.putExtra("name", edit_name.text.toString())
                intent.putExtra("volume", volume)
                intent.putExtra("state", state)
                intent.putExtra("index", index)
                setResult(RESULT_OK, intent)

                finish()
            }
        }
        btn_cancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        btn_delete.setOnClickListener {
            intent.putExtra("state",2)
            intent.putExtra("name","")
            intent.putExtra("volume",-1)
            intent.putExtra("index", index)
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}