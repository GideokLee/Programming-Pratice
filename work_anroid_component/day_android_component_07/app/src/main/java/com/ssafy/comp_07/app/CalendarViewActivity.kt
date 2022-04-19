package com.ssafy.comp_07.app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_07.app.databinding.ActivityCalendarViewBinding
import com.ssafy.comp_07.app.progressbar.ProgressBarActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


private const val TAG = "CalendarViewActivity_싸피"
class CalendarViewActivity : AppCompatActivity() {

    lateinit var binding : ActivityCalendarViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /********** 캘린더에 특정 날짜 세팅 (어제 날짜 선택으로) ***********/
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1) //날짜를 하루 뺀다.

        //calendarView에는 날짜 long값을 세팅한다.
        binding.calendarView.date = cal.time.time  //cal.time은 Date를 리턴하고, Date.time은 long값 리턴.
        /*********************/


        // 캘린더 인스턴스 가져오기
        val calendar = Calendar.getInstance()

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // 캘린더 인스턴스에 CalendarView 에서 선택한 날짜 세팅
            calendar.set(year, month, dayOfMonth)

            // 날짜 표기법 Format
//            val dateFormatter = DateFormat.getDateInstance(DateFormat.MEDIUM)
//            val formattedDate = dateFormatter.format(calendar.time)
            var dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
            dateFormatter.timeZone = TimeZone.getTimeZone("Asia/Seoul")
            val formattedDate = dateFormatter.format(calendar.time)
            // TextView 에 날짜 세팅하기
            binding.tvDate.text = formattedDate
        }

        val today = Date()
        val dateFull = DateFormat.getDateInstance(DateFormat.FULL, Locale.KOREA)
        val dateLong = DateFormat.getDateInstance(DateFormat.LONG, Locale.KOREA)
        val dateMedium = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.KOREA)
        val dateShort = DateFormat.getDateInstance(DateFormat.SHORT, Locale.KOREA)

        Log.d(TAG, "FULL : ${dateFull.format(today)}")
        Log.d(TAG, "LONG : ${dateLong.format(today)}")
        Log.d(TAG, "MEDIUM : ${dateMedium.format(today)}")
        Log.d(TAG, "SHORT : ${dateShort.format(today)}")
        
        binding.btn.setOnClickListener {
            startActivity(Intent(this, ProgressBarActivity::class.java))
        }
    }
}