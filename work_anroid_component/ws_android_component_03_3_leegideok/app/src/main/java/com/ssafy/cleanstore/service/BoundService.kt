package com.ssafy.cleanstore.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.ssafy.cleanstore.dao.StuffDao
import com.ssafy.cleanstore.dto.Stuff

class BoundService : Service() {

    private lateinit var myBinder : IBinder


    override fun onBind(intent: Intent): IBinder {
        myBinder = MyBinder()
        return myBinder

    }
    fun selectAll() : MutableList<Stuff>{

    }
    fun stuffInsert(stuff: Stuff): Long{
    }
}

class MyBinder : Binder(){
    private lateinit var stuffDao: StuffDao

    fun getService() : BoundService = BoundService()
}