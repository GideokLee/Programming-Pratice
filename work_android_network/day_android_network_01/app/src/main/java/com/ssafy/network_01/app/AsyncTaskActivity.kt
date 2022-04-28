package com.ssafy.network_01.app

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.TextView
import com.ssafy.network_01.app.databinding.ActivityAsyncTaskBinding

private const val TAG = "AsyncTaskActivity_싸피"
@Suppress("DEPRECATION")
class AsyncTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsyncTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAsyncTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val asyncTask = MyAsyncTask(binding.textView)
        asyncTask.execute()
        Log.d(TAG, "onCreate: ")
    }

    inner class MyAsyncTask(textView: TextView) : AsyncTask<Void, Int, Boolean>() {

        override fun doInBackground(vararg params: Void?): Boolean {
            Log.d(TAG, "doInBackground: ")
            for (i in 0..100) {
                SystemClock.sleep(100)
                publishProgress(i)
            }
            return true
        }

        override fun onPreExecute() {
            super.onPreExecute()
            Log.d(TAG, "onPreExecute: ")
        }

        override fun onPostExecute(result: Boolean?) {
            super.onPostExecute(result)
            Log.d(TAG, "onPostExecute: ")
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            Log.d(TAG, "onProgressUpdate: ")

            //vararg : 가변인자, "*"을 붙이면 가변인자로 취급
            binding.textView.text = values[0].toString()
        }

        override fun onCancelled(boolean: Boolean) {
            super.onCancelled()
            Log.d(TAG, "onCancelled: ")
        }
    }
}