package com.ssafy.cleanstore.stuff

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputLayout
import com.ssafy.cleanstore.R
import com.ssafy.cleanstore.databinding.ActivityStuffEditBinding
import com.ssafy.cleanstore.dto.Stuff
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

private const val TAG = "StuffEditActivity_싸피"

class StuffEditActivity : AppCompatActivity() {
    private val REGISTER = 0
    private val DELETE = 1
    private val MODIFY = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stuff_edit)

        //에딧텍스트
        val etStuffName = findViewById<EditText>(R.id.et_stuff_edit_stuff_name)
        val inputLayoutStuffCount = findViewById<TextInputLayout>(R.id.text_input_layout_edit_stuff_count)
        val etStuffCount = findViewById<EditText>(R.id.et_stuff_edit_stuff_count)
        // 버튼
        val btnSave = findViewById<Button>(R.id.btn_stuff_edit_save)
        val btnDelete = findViewById<Button>(R.id.btn_stuff_edit_delete)
        val btnCancel = findViewById<Button>(R.id.btn_stuff_edit_cancel)
        // 달력
        val calendarView = findViewById<CalendarView>(R.id.calendar_view_edit_stuff_date)
        // 날짜 텍스트 뷰
        val tvStuffDate = findViewById<TextView>(R.id.tv_stuff_edit_date)

        // List에서 넘어오는 값
        val stuff = intent.getSerializableExtra("stuff") as Stuff
        val actionFlag: Int = intent.getIntExtra("ActionFlag", -1)

        when(actionFlag){
            MODIFY, DELETE -> {
                btnDelete.visibility = View.VISIBLE

                val stuffRegDate: String = stuff.regDate
                // 넘어온 값 보여주기
                etStuffName.setText(stuff.name)
                etStuffCount.setText(stuff.count.toString())
                calendarView.setDate(SimpleDateFormat("yy / M / d", Locale.KOREA).parse(stuffRegDate)!!.time,true,true)
                tvStuffDate.text = stuffRegDate
            }
            REGISTER -> {
                // 등록의 경우 현재 날짜로 세팅
                tvStuffDate.text = SimpleDateFormat("yy / M / d", Locale.KOREA).format(Date())
                btnDelete.visibility = View.GONE
            }
        }

        // 물품 수량에 숫자 이외 못넣도록 에러 지정
        etStuffCount.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if(s.toString().contains(Regex("[^\\d]"))){
                    inputLayoutStuffCount.error = "숫자만 넣을 수 있습니다."
                }
                else{
                    inputLayoutStuffCount.error = null
                }
            }

        })

        // 날짜 지정
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            tvStuffDate.text = String.format("%d / %d / %d", year-2000, month + 1, dayOfMonth)
        }

        // 저장
        btnSave.setOnClickListener {
            // 현재 수정, 등록된 값 가져오기
            val name : String = etStuffName.text.toString()
            val count : String = etStuffCount.text.toString()
            val regDate : String = tvStuffDate.text.toString()

            //값이 다 채워졌다면
            if (name.isNotEmpty() && count.isNotEmpty() && regDate.isNotEmpty()) {

                if (inputLayoutStuffCount.error != null) {
                    Toast.makeText(this, "수량은 숫자만 입력 가능합니다.", Toast.LENGTH_SHORT).show()
                }
                else {
                    //값 전달 Intent 생성
                    val outIntent = Intent(this, StuffActivity::class.java)
                    //전달할값 넣기
                    outIntent.putExtra("stuff", Stuff(stuff.id, name, count.toInt(), regDate))
                    outIntent.putExtra("OActionFlag", actionFlag)
                    setResult(Activity.RESULT_OK, outIntent)
                    //해당 엑티비티 종료
                    finish()
                }

            } else Toast.makeText(this, "모든 빈칸을 채워주세요", Toast.LENGTH_SHORT).show()
        }

        // 삭제
        btnDelete.setOnClickListener{
            //값 전달 Intent 생성
            val outIntent = Intent(this, StuffActivity::class.java)
            //전달할값 넣기
            outIntent.putExtra("stuff", stuff)
            outIntent.putExtra("OActionFlag", DELETE)
            setResult(Activity.RESULT_OK, outIntent)
            //해당 엑티비티 종료
            finish()
        }

        //취소
        btnCancel.setOnClickListener {
            //해당 엑티비티 종료
            finish()
        }
    }
}