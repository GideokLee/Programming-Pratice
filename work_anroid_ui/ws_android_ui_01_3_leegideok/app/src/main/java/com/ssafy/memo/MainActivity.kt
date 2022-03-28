package com.ssafy.memo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.memo.databinding.ActivityMainBinding
class MainActivity: AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val intent =Intent(this, MemoEditActivity::class.java)

            memoEditActivityLauncher.launch(intent)
        }
        binding.btnLookup.setOnClickListener {
            val intent = Intent(this, MemoInfoActivity::class.java)
            startActivity(intent)
        }
    }

    private val memoEditActivityLauncher : ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        if(it.resultCode == Activity.RESULT_OK){
            val intent = it.data
            val returnValue = intent!!.getStringExtra("todo")
            binding.editMemo.setText(returnValue)
        }
    }
}
