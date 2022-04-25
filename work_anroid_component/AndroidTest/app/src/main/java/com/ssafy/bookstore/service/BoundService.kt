package com.ssafy.bookstore.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.ssafy.bookstore.dao.BookDao
import com.ssafy.bookstore.dto.Book

private const val TAG = "BoundService_싸피"
class BoundService : Service() {
    // Binder given to clients
    private val myBinder = MyBinder()

    private val bookDAO = BookDao()

    inner class MyBinder : Binder() {
        // Service 객체를 return
        fun getService(): BoundService = this@BoundService
    }

    // 물품 추가
    fun bookInsert(book: Book): Long {
        return bookDAO.bookInsert(book)
    }


    // 물품 모두 조회
    fun bookSelectAll(): MutableList<Book> {
        return bookDAO.bookSelectAll()
    }


    // Bound Service에서 서비스 연결시 호출
    override fun onBind(intent: Intent?): IBinder {
        return myBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onCreate() {
        bookDAO.dbOpenHelper(this)
        bookDAO.open()
        bookDAO.create()
        super.onCreate()
    }

    override fun onDestroy() {
        bookDAO.close()
        super.onDestroy()
    }
}