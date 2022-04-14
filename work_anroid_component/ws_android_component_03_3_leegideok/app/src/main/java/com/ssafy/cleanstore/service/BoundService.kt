package com.ssafy.cleanstore.service
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.ssafy.cleanstore.dao.StuffDao
import com.ssafy.cleanstore.dto.Stuff

class BoundService : Service() {

    private lateinit var myBinder : IBinder

    private lateinit var stuffDao: StuffDao

    override fun onBind(intent: Intent): IBinder {
        myBinder = MyBinder()
        return myBinder

    }
    fun selectAll() : MutableList<Stuff>{
        return stuffDao.stuffSelectAll()
    }
    fun stuffInsert(stuff: Stuff): Long{
        return stuffDao.stuffInsert(stuff)
    }

    override fun onCreate() {
        super.onCreate()
        stuffDao = StuffDao()
        stuffDao.dbOpenHelper(this)
        stuffDao.open()
    }
}

class MyBinder : Binder(){

    fun getService() : BoundService = BoundService()
}