package com.ssafy.network_02.board

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ssafy.network_02.board.databinding.ActivityDetailBinding
import com.ssafy.network_02.board.model.Board
import com.ssafy.network_02.board.service.BoardService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "DetailActivity_싸피"
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var no = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        no = intent.getStringExtra("no")?.toInt() ?: -1
        Log.d(TAG, "onCreate: $no")

        if (savedInstanceState != null) {
            no = savedInstanceState.getInt("no")
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
        initEvent()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("no", no.toString())
    }

    private fun getData() {
        val service = ApplicationClass.retrofit.create(BoardService::class.java)
        service.selectBoard(no.toString()).enqueue(object : Callback<Board> {
            override fun onResponse(call: Call<Board>, response: Response<Board>) {
                if (response.isSuccessful) {
                    fillData(response.body() as Board)
                }
                else {
                    Log.d(TAG, "selectBoard - onResponse : Error code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Board>, t: Throwable) {
                Log.d(TAG, t.message ?: "통신오류")
            }
        })
    }

    private fun fillData(board: Board) {
        Log.d(TAG, "fillData: $board")
        binding.tvNo.text = board.no.toString()
        binding.tvTitle.text = board.title
        binding.tvWriter.text = board.writer
        binding.tvContent.text = board.content
        binding.tvRegtime.text = board.regtime
    }

    private fun initEvent() {

        // 수정 버튼
        binding.btnEdit.setOnClickListener {
            startActivity(Intent(this, WriteActivity::class.java).apply {
                putExtra("no", no.toString())
            })
        }

        // 삭제 버튼
        binding.btnDelete.setOnClickListener {

            // 다이얼로그
            val listener = DialogInterface.OnClickListener { dialog, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        deleteData()
                    }
                    DialogInterface.BUTTON_NEGATIVE -> {
                        Toast.makeText(this@DetailActivity,
                            "삭제 취소",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

            AlertDialog.Builder(this)
                .setTitle("삭제 확인")
                .setMessage("정말로 삭제 할까요?")
                .setIcon(R.drawable.ic_baseline_warning_24)
                .setPositiveButton("삭제", listener)
                .setNegativeButton("취소", listener)
                .show()
        }

        // 이전 버튼
        binding.buttonClose.setOnClickListener {
            finish()
        }
    }

    private fun deleteData() {
        val service = ApplicationClass.retrofit.create(BoardService::class.java)
        service.deleteBoard(no.toString()).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    finish()
                }
                else {
                    Log.d(TAG, "deleteBoard - onResponse : Error code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d(TAG, t.message ?: "통신오류")
            }
        })
    }
}