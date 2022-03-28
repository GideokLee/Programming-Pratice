package com.ssafy.memo

import android.R
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ssafy.memo.databinding.ActivityMemoEditBinding

class MemoEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMemoEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSubmit.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)

            if(binding.editDetail.text.isEmpty() || binding.editTodo.text.isEmpty()){
                Toast.makeText(this, "값을 입력 해주세요.", Toast.LENGTH_SHORT).show()

            }else{
                intent.putExtra("todo", binding.editTodo.text.toString())
                intent.putExtra("detail",binding.editDetail.text.toString())

                setResult(Activity.RESULT_OK, intent)

                finish()
            }
        }
        binding.btnCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            setResult(Activity.RESULT_CANCELED, intent)

            finish()
        }
    }
}