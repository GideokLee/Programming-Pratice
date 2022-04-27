package com.ssafy.gallery

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters

class GalleryWorker(context: Context, params: WorkerParameters) : Worker(context,params) {
    companion object{
        const val EXTRA_COUNT = "EXTRA_NUMBER"
        const val EXTRA_RESULT = "EXTRA_RESULT"
        var count = -1
    }

    override fun doWork(): Result {
        val currCount = inputData.getInt(EXTRA_COUNT,0)
        var changed = false

        if(count != currCount){
            changed = true
            count = currCount
        }

        val outputData = Data.Builder()
            .putBoolean(EXTRA_RESULT,changed)
            .build()


        Log.d("Worker",outputData.getBoolean(EXTRA_RESULT,false).toString())
        return Result.success(outputData)
    }

}