package com.ssafy.ssafygo.storeReview

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.ssafygo.R
import com.ssafy.ssafygo.dto.StoreReviewDTO

class StoreReviewEditActivity : AppCompatActivity() {
    private val REGISTER = 0
    private val DELETE = 1
    private val MODIFY = 2

    private lateinit var etStoreReviewContent: EditText
    private lateinit var rbStoreReviewScore: RatingBar

    private var storeId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_review_edit)

        // Content
        etStoreReviewContent = findViewById(R.id.et_store_review_edit_content)

        // Score
        rbStoreReviewScore = findViewById(R.id.rb_store_review_edit_score)

        // 버튼
        val btnSave = findViewById<Button>(R.id.btn_store_review_edit_save)
        val btnDelete = findViewById<Button>(R.id.btn_store_review_edit_delete)
        val btnCancel = findViewById<Button>(R.id.btn_store_review_edit_cancel)

        // List에서 넘어오는 값
        val actionFlag: Int = intent.getIntExtra("ActionFlag", -1)

        val storeReview = intent.getSerializableExtra("StoreReview") as StoreReviewDTO

        setStoreReview(storeReview)
        when (actionFlag) {
            MODIFY -> {
                btnDelete.visibility = View.VISIBLE
            }
            REGISTER -> {
                btnDelete.visibility = View.GONE
            }
        }

        // 저장
        btnSave.setOnClickListener {
            // 현재 수정, 등록된 값 가져오기
            val content : String = etStoreReviewContent.text.toString()
            val score = rbStoreReviewScore.rating.toInt()

            // 값이 다 채워졌다면
            if (content.isNotEmpty()) {
                setResult(
                    Activity.RESULT_OK,
                    Intent(applicationContext, StoreReviewActivity::class.java).apply {
                        putExtra("OStoreReview", StoreReviewDTO(storeReview.id, content, score, storeId))
                        putExtra("OActionFlag", actionFlag)
                    }
                )

                // 해당 엑티비티 종료
                finish()
            }
            else {
                Toast.makeText(this@StoreReviewEditActivity,
                    "모든 빈칸을 채워주세요",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // 삭제
        btnDelete.setOnClickListener {
            setResult(
                Activity.RESULT_OK,
                Intent(applicationContext, StoreReviewActivity::class.java).apply {
                    putExtra("OStoreReview", storeReview)
                    putExtra("OActionFlag", DELETE)
                }
            )

            // 해당 엑티비티 종료
            finish()
        }

        // 취소
        btnCancel.setOnClickListener {
            // 해당 엑티비티 종료
            finish()
        }
    }

    private fun setStoreReview(storeReview: StoreReviewDTO?) {
        if (storeReview != null) {
            val content: String = storeReview.content
            val score = storeReview.score
            storeId = storeReview.storeId

            // 리뷰의 내용과 점수
            etStoreReviewContent.setText(content)
            rbStoreReviewScore.rating = score.toFloat()
        }
    }
}