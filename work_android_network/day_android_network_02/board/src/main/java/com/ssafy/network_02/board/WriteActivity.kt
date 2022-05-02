package com.ssafy.network_02.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.ssafy.network_02.board.databinding.ActivityWriteBinding
import com.ssafy.network_02.board.model.Board
import com.ssafy.network_02.board.service.BoardService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "WriteActivity_싸피"
class WriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteBinding
    private var no = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        no = intent.getStringExtra("no")?.toInt() ?: -1  // 수정일 경우 -1이 아닌 값

        if ( no >= 0 ) {
            getData()
        }

        initEvent()
    }

    private fun initEvent() {
        binding.buttonSave.setOnClickListener {
            if (no >= 0) {
                update()
            }
            else {
                save()
            }
        }

        binding.buttonInit.setOnClickListener {
            binding.etTitle.setText("")
            binding.etContent.setText("")
        }

        binding.buttonClose.setOnClickListener {
            finish()
        }
    }

    private fun save() {
        var board = Board().apply {
            writer = binding.etWriter.text.toString()
            title = binding.etTitle.text.toString()
            content = binding.etContent.text.toString()
        }

        val service = ApplicationClass.retrofit.create(BoardService::class.java)
        service.insertBoard(board).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@WriteActivity,
                        "저장하였습니다.",
                        Toast.LENGTH_SHORT)
                        .show()

                    finish()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d(TAG, t.message ?: "통신오류")
            }
        })
    }

    private fun update() {
        var board = Board().apply {
            writer = binding.etWriter.text.toString()
            title = binding.etTitle.text.toString()
            content = binding.etContent.text.toString()
        }

        val service = ApplicationClass.retrofit.create(BoardService::class.java)
        service.updateBoard(no.toString(), board).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@WriteActivity,
                        "수정하였습니다.",
                        Toast.LENGTH_SHORT)
                        .show()

                    finish()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.d(TAG, t.message ?: "통신오류")
            }
        })
    }

    private fun initView(board: Board) {
        // 수정으로 제목변경
        binding.tvTitle.text = "게시글 수정"

        binding.tvNo.text = board.no.toString()
        binding.tvRegtime.text = board.regtime

        // EditText는 setText로 assign
        binding.etWriter.setText(board.writer)
        binding.etTitle.setText(board.title)
        binding.etContent.setText(board.content)
    }

    private fun getData() {
        val service = ApplicationClass.retrofit.create(BoardService::class.java)
        service.selectBoard(no.toString()).enqueue(object : Callback<Board> {
            override fun onResponse(call: Call<Board>, response: Response<Board>) {
                if (response.isSuccessful) {
                    initView(response.body() as Board)
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
}