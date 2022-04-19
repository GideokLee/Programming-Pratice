package com.ssafy.comp_07.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_07.app.progressbar.ProgressBarActivity
import com.ssafy.comp_07.app.progressbar.ProgressBarActivity2


class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.button_1).setOnClickListener{
            var intent = Intent(this, WebViewActivity::class.java);
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_2).setOnClickListener{
            var intent = Intent(this, VideoViewActivity::class.java);
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_3).setOnClickListener{
            var intent = Intent(this, CalendarViewActivity::class.java);
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_4).setOnClickListener{
            var intent = Intent(this, ProgressBarActivity::class.java);
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_5).setOnClickListener{
            var intent = Intent(this, ProgressBarActivity2::class.java);
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_6).setOnClickListener{
            var intent = Intent(this, SeekBarActivity::class.java);
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_7).setOnClickListener{
            var intent = Intent(this, RatingBarActivity::class.java);
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_8).setOnClickListener{
            var intent = Intent(this, SearchViewActivity::class.java);
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_9).setOnClickListener{
            var intent = Intent(this, TextureViewActivity::class.java);
            startActivity(intent)
        }
    }

}