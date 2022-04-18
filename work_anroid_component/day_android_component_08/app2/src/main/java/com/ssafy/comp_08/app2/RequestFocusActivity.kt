package com.ssafy.comp_08.app2

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.comp_08.app2.databinding.ActivityRequestFocusBinding


private const val TAG = "RequestFocusActivity_싸피"
class RequestFocusActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRequestFocusBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRequestFocusBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
//        binding.tvMain.requestFocus()
        toggleView()
    }

    fun toggleView() {
        if (currentFocus?.visibility == View.VISIBLE) {
            Toast.makeText(this, "invisiable~", Toast.LENGTH_SHORT).show()
            currentFocus?.visibility = View.INVISIBLE
        } else {
            binding.tvMain.visibility = View.VISIBLE
            Toast.makeText(this, "visiable!!", Toast.LENGTH_SHORT).show()
        }
    }
}


