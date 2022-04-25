package com.ssafy.gallery.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ssafy.gallery.dto.Photo
import java.sql.SQLException

class GalleryDao {
    lateinit var helper: DBHelper
    lateinit var sqlDB: SQLiteDatabase
    private var mCtx: Context? = null

    private val DB_NAME = "photos"
    private val TABLE_NAME = "photos"
    private val PHOTO_ID = "num"
    private val PHOTO_LOCATION = "photoLocation"
    private val PHOTO_DATE = "photoDate"
    private val PHOTO_SRC = "photoSrc"

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

    fun selectAll(): MutableList<Photo>{
        val list: ArrayList<Photo> = arrayListOf()
        sqlDB.rawQuery("SELECT * FROM $TABLE_NAME", null).use{
            if(it.moveToFirst()){
                do{
                    list.add(Photo(it.getString(1),it.getLong(2),it.getString(3)))
                }while (it.moveToNext())
            }
        }
        return list
    }

    inner class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
        // 테이블 생성
        override fun onCreate(db: SQLiteDatabase?) {
//            db!!.execSQL("""CREATE TABLE if not exists $TABLE_NAME (
//               PHOTO$PHOTO_ID INTEGER PRIMARY KEY AUTOINCREMENT,
//                    $PHOTO_LOCATION VARCHAR(200),
//                    $PHOTO_SRC VARCHAR(200),
//                    $PHOTO_DATE LONG
//                )
//                """)
        }

        override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) { //테이블 삭제 후 생성
            db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }
    }
}