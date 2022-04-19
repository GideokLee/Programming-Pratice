package com.ssafy.cleanstore.stuff

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.ssafy.cleanstore.R
import com.ssafy.cleanstore.dto.Stuff
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

private const val TAG = "StuffEditActivity_싸피"
class StuffEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stuff_edit)

        // 에딧텍스트
        val etStuffName = findViewById<TextInputEditText>(R.id.et_stuff_edit_stuff_name)
        val etStuffCount = findViewById<TextInputEditText>(R.id.et_stuff_edit_stuff_count)

        // 버튼
        val btnSave = findViewById<Button>(R.id.btn_stuff_edit_save)
        val btnDelete = findViewById<Button>(R.id.btn_stuff_edit_delete)
        val btnCancel = findViewById<Button>(R.id.btn_stuff_edit_cancel)

        // List에서 넘어오는 값
//        val name = intent.getStringExtra("Name")
//        val count = intent.getIntExtra("Count", -1)
        val stuff = intent.getSerializableExtra("stuff") as Stuff
        val position = intent.getIntExtra("Position", -1)
        val actionFlag = intent.getSerializableExtra("ActionFlag")

        val calendarView = findViewById<CalendarView>(R.id.calendar_view)
        val cal = Calendar.getInstance()

        val tvDate = findViewById<TextView>(R.id.tv_date)
        var currDate : Long = 0L


        when (actionFlag) {
            ActionFlag.REGISTER -> {
                calendarView.date = cal.time.time

                btnDelete.visibility = View.GONE
                tvDate.text = this.formatter().format(cal.timeInMillis)
            }
            ActionFlag.MODIFY -> {
                btnDelete.visibility = View.VISIBLE
                // 넘어온 값 보여주기
                etStuffName.setText(stuff.name)
                etStuffCount.setText(stuff.count.toString())
                tvDate.text = this.formatter().format(stuff.regDate)
                calendarView.date = stuff.regDate
            }
        }
        // 저장
        btnSave.setOnClickListener {

            // 값이 다 채워졌다면
            if (etStuffName.text.toString().isNotEmpty() && etStuffCount.text.toString().isNotEmpty()) {

                // 값 전달 Intent 생성
                Intent(this, StuffActivity::class.java).apply {

                    // 전달할 값 넣기
//                    putExtra("OName", etStuffName.text.toString())
//                    putExtra("OCount", etStuffCount.text.toString().toInt())
                    val stuff = Stuff(etStuffName.text.toString(), etStuffCount.text.toString().toInt(), currDate)
                    putExtra("stuff", stuff)
                    putExtra("OPosition", position)
                    putExtra("OActionFlag", actionFlag)
                    setResult(Activity.RESULT_OK, this)
                }

                // 해당 엑티비티 종료
                finish()
            }
            else {
                Toast
                    .makeText(this, "모든 빈칸을 채워주세요", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        // 삭제
        btnDelete.setOnClickListener {

            // 값 전달 Intent 생성
            Intent(this, StuffActivity::class.java).apply {

                // 전달할값 넣기
                putExtra("OPosition", position)
                putExtra("OActionFlag", ActionFlag.DELETE)
                setResult(Activity.RESULT_OK, this)
            }

            // 해당 엑티비티 종료
            finish()
        }

        // 취소
        btnCancel.setOnClickListener {
            // 해당 엑티비티 종료
            finish()
        }

        val calendar = Calendar.getInstance()
        calendarView.setOnDateChangeListener{ view, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val formattedDate = this.formatter().format(calendar.time)
            currDate = calendar.time.time
            tvDate.text = formattedDate
        }

    }
    fun formatter() : SimpleDateFormat{
        val formatter = SimpleDateFormat("yy / M / dd")
        formatter.timeZone = TimeZone.getTimeZone("Asia/Seoul")

        return formatter
    }

}