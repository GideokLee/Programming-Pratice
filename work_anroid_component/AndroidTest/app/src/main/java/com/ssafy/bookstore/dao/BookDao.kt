package com.ssafy.bookstore.dao

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ssafy.bookstore.dto.Book
import java.sql.SQLException

class BookDao {

    lateinit var helper: DBHelper
    lateinit var sqlDB: SQLiteDatabase
    private var mCtx: Context? = null

    private val DB_NAME = "book"
    private val TABLE_NAME = "book"
    private val BOOK_ID = "_id"
    private val BOOK_TITLE = "title"
    private val BOOK_AUTHOR = "author"
    private val BOOK_CONTENT = "content"
    private val BOOK_PRICE = "price"
    private val BOOK_DATE = "date"

    @Throws(SQLException::class)
    fun open() {
        helper = DBHelper(mCtx!!)
        sqlDB = helper.writableDatabase
    }
    fun dbOpenHelper(context: Context) {
        this.mCtx = context
    }
    fun create() {
        //DB생성
        helper.onCreate(sqlDB)
    }
    fun upgrade(oldVersion: Int, newVersion: Int) {
        //DB version 변경
        helper.onUpgrade(sqlDB, 1, 2)
    }
    fun close() {
        //DB종료
        sqlDB.close()
    }

    fun bookInsert(book: Book):Long{
        val args = ContentValues()
        args.put(BOOK_TITLE,book.title)
        args.put(BOOK_AUTHOR,book.author)
        args.put(BOOK_CONTENT, book.content)
        args.put(BOOK_PRICE, book.price)
        args.put(BOOK_DATE, book.date)
        return sqlDB.insert(TABLE_NAME, null, args)
    }
    fun bookSelectAll() : MutableList<Book>{
        val list: ArrayList<Book> = arrayListOf()
        sqlDB.rawQuery("SELECT $BOOK_ID, $BOOK_TITLE, $BOOK_AUTHOR, $BOOK_CONTENT, $BOOK_PRICE, $BOOK_DATE FROM $TABLE_NAME", null).use{
            if(it.moveToFirst()){
                do{
                    list.add(Book(it.getInt(0),it.getString(1), it.getString(2), it.getString(3), it.getInt(4), it.getString(5)))
                }while (it.moveToNext())
            }
        }
        return list
    }

    inner class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {

        // 테이블 생성
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL("""CREATE TABLE if not exists $TABLE_NAME (
                    $BOOK_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                    $BOOK_TITLE VARCHAR(50),
                    $BOOK_AUTHOR VARCHAR(50),
                    $BOOK_CONTENT VARCHAR(50),
                    $BOOK_PRICE INTEGER,
                    $BOOK_DATE VARCHAR(50)
                )
                """)
        }

        override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) { //테이블 삭제 후 생성
            db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }
}