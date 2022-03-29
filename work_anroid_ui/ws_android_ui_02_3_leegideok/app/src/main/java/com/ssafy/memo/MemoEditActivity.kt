package com.ssafy.memo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ssafy.memo.databinding.ActivityMemoEditBinding
import com.ssafy.memo.util.Utils


class MemoEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemoEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoEditBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val type = intent.getStringExtra("type")
        val index = intent.getIntExtra("index",-1)

        if(type.equals("modify")){
            var item = intent.getSerializableExtra("memoItem") as MemoItem
            binding.layoutDate.visibility = View.VISIBLE
            binding.btnRemove.visibility = View.VISIBLE
            binding.editTitle.setText(item.title)
            binding.editDetail.setText(item.content)
            binding.editDate.setText(item.date)
        }else{
            binding.editTitle.isEnabled = true
        }

        binding.btnRemove.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("index", index)
            intent.putExtra("type","remove")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
        binding.btnRegister.setOnClickListener {

            if(binding.editDetail.text.isEmpty() || binding.editTitle.text.isEmpty()){
                Toast.makeText(this, "값을 입력 해주세요.", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                val title = binding.editTitle.text.toString()
                val content = binding.editDetail.text.toString()
                val date = binding.editDate.text.toString()
                var item = MemoItem(title,content,date)
                if(type.equals("modify")){
                    intent.putExtra("index", index)
                    intent.putExtra("type","modify")
                }else{
                    item.date = Utils.formatter().format(System.currentTimeMillis()).toString()
                    intent.putExtra("type","register")
                }
                intent.putExtra("item",item)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        binding.btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}