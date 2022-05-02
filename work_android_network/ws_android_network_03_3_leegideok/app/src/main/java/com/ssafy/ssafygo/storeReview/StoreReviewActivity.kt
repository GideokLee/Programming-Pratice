package com.ssafy.ssafygo.storeReview

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.ssafy.ssafygo.ApplicationClass
import com.ssafy.ssafygo.R
import com.ssafy.ssafygo.dto.StoreReviewDTO
import com.ssafy.ssafygo.service.StoreReviewService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "StoreReviewActivity_싸피"
class StoreReviewActivity : AppCompatActivity() {
    private val REGISTER = 0
    private val DELETE = 1
    private val MODIFY = 2

    private var storeId = 1

    private var storeReviewList: ArrayList<StoreReviewDTO> = arrayListOf()
    private lateinit var adapter: ArrayAdapter<StoreReviewDTO>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_review)

        storeId = intent.getIntExtra("StoreId", -1)

        val listview: ListView = findViewById(R.id.listview_store_review)

        // Adapter와 ListView 연결
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, storeReviewList)
        listview.adapter = adapter

        // Intent 사용
        val requestActivity: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { activityResult ->
            if (activityResult.resultCode == Activity.RESULT_OK) {
                val intent: Intent? = activityResult.data
                val storeReview = intent?.getSerializableExtra("OStoreReview") as StoreReviewDTO

                Log.d(TAG, "onCreate: $storeReview")

                // 삭제, 등록 또는 수정
                when (intent.getIntExtra("OActionFlag", -1)) {
                    REGISTER -> {
                        setStoreReviewInfo(storeReview)
                    }
                    MODIFY -> {
                        putStoreReviewInfo(storeId, storeReview)
                    }
                    DELETE ->{
                        deleteStoreReviewInfo(storeReview.id)
                    }
                }
            }
        }

        // 등록 버튼 연결
        val btnRegister = findViewById<Button>(R.id.btn_store_review_register)

        // 리뷰 등록
        btnRegister.setOnClickListener {
            requestActivity.launch(Intent(this, StoreReviewEditActivity::class.java).apply {
                putExtra("StoreReview", StoreReviewDTO("", -1, storeId))
                putExtra("ActionFlag", REGISTER)
            })
        }

        // 리뷰 수정
        listview.setOnItemClickListener { parent, view, position, id ->
            val storeReview = storeReviewList[position]
            requestActivity.launch(Intent(this, StoreReviewEditActivity::class.java).apply {
                putExtra("StoreReview", storeReview)
                putExtra("ActionFlag", MODIFY)
            })
        }

        getStoreReviewInfo(storeId)
    }

    // 리뷰 정보 모두 가져오기
    private fun getStoreReviewInfo(storeId: Int) {
        val storeService = ApplicationClass.retrofit.create(StoreReviewService::class.java)
        // enqueue를 통해 비동기적으로 API 호출 작업 수행
        storeService.getStoreReviews(storeId).enqueue(object : Callback<List<StoreReviewDTO>> {
            override fun onResponse(call: Call<List<StoreReviewDTO>>, response: Response<List<StoreReviewDTO>>) {
                val res = response.body()
                if (response.code() == 200) {
                    storeReviewList.clear()
                    if (res != null) {
                        storeReviewList.addAll(res)
                    }
                    else {
                        Toast.makeText(this@StoreReviewActivity,
                            "리뷰정보를 가져올 수 없습니다.",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                    adapter.notifyDataSetChanged()
                    Log.d(TAG, "onResponse: $res")
                }
                else {
                    Log.d(TAG, "onResponse: Error Code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<StoreReviewDTO>>, t: Throwable) {
                Log.d(TAG, t.message ?: "리뷰 정보 불러오는 중 통신오류")
            }
        })
    }

    // 리뷰 등록
    private fun setStoreReviewInfo(storeReview: StoreReviewDTO) {
        val storeService = ApplicationClass.retrofit.create(StoreReviewService::class.java)
        storeService.setStoreReview(storeReview).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val res = response.body()
                if (response.code() == 200) {
                    if (res == true) {
                        getStoreReviewInfo(storeId)
                    }
                    else {
                        Toast.makeText(this@StoreReviewActivity,
                            "리뷰가 등록되지 않았습니다.",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                    Log.d(TAG, "onResponse: $res")
                }
                else {
                    Log.d(TAG, "onResponse: Error Code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d(TAG, t.message ?: "리뷰 정보 등록 중 통신오류")
            }
        })
    }

    // 리뷰 수정
    private fun putStoreReviewInfo(storeId: Int, storeReview: StoreReviewDTO) {
        val storeService = ApplicationClass.retrofit.create(StoreReviewService::class.java)
        storeService.putStoreReview(storeId, storeReview).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val res = response.body()
                if (response.code() == 200) {
                    if (res == true) {
                        getStoreReviewInfo(storeId)
                    }
                    else {
                        Toast.makeText(this@StoreReviewActivity, "리뷰가 수정되지 않았습니다.", Toast.LENGTH_SHORT).show()
                    }
                    Log.d(TAG, "onResponse: $res")
                }
                else {
                    Log.d(TAG, "onResponse: Error Code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d(TAG, t.message ?: "리뷰 정보 수정 중 통신오류")
            }
        })
    }

    // 리뷰 삭제
    private fun deleteStoreReviewInfo(storeId: Int) {
        val storeService = ApplicationClass.retrofit.create(StoreReviewService::class.java)
        storeService.deleteStoreReview(storeId).enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                val res = response.body()
                if (response.code() == 200) {
                    if (res == true) {
                        getStoreReviewInfo(storeId)
                    }
                    else {
                        Toast.makeText(this@StoreReviewActivity, "리뷰가 삭제되지 않았습니다.", Toast.LENGTH_SHORT).show()
                    }
                    Log.d(TAG, "onResponse: $res")
                }
                else {
                    Log.d(TAG, "onResponse: Error Code ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.d(TAG, t.message ?: "리뷰 정보 삭제 중 통신오류")
            }
        })
    }
}