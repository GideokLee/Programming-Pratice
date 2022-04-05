package com.ssafy.memo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.SQLException

class MemoDao {

    // DB선언부
    private lateinit var helper: DBHelper
    private lateinit var sqlDB: SQLiteDatabase
    private var mCtx: Context? = null

    private val DATABASE_TABLE = "memos"
    private val NUM = "num"
    private val MEMO_TITLE = "memoTitle"
    private val MEMO_CONTENT = "memoContent"
    private val MEMO_DATE = "memoDate"

    // INSERT
    fun insertMemo(dto: MemoDto): Long {
        val contentValues = ContentValues()
        contentValues.put(MEMO_TITLE, dto.title)
        contentValues.put(MEMO_CONTENT, dto.content)
        contentValues.put(MEMO_DATE, dto.date)
        sqlDB.beginTransaction()
        val result = sqlDB.insert(DATABASE_TABLE, null, contentValues)
        if(result > 0){
            sqlDB.setTransactionSuccessful()
        }
        sqlDB.endTransaction()
        return result
    }

    // UPDATE
    fun updateMemo(dto: MemoDto): Int {
        val contentValues = ContentValues()
        contentValues.put(MEMO_CONTENT, dto.content)
        sqlDB.beginTransaction()
        val result = sqlDB.update(DATABASE_TABLE,contentValues,"num=?", arrayOf(dto.num.toString()));
        if(result > 0){
            sqlDB.setTransactionSuccessful()
        }
        sqlDB.endTransaction()
        return result
    }

    // SELECT
    fun selectAllMemos(): MutableList<MemoDto> {
        val columns = arrayOf(NUM, MEMO_TITLE, MEMO_CONTENT, MEMO_DATE)
        val cursor = sqlDB.query(DATABASE_TABLE, columns, null, null, null, null, null,null)
        var result = mutableListOf<MemoDto>()
        while(cursor.moveToNext()){
            var row = MemoDto(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3))
            result.add(row)
        }
        return result
    }

    fun selectMemo(num: Int): MemoDto {
        val columns = arrayOf(NUM, MEMO_TITLE, MEMO_CONTENT, MEMO_DATE)
        val cursor = sqlDB.query(DATABASE_TABLE, columns, "num=?", arrayOf(num.toString()), null, null, null,null)
        var result : MemoDto = MemoDto(-1,"","","")
        if(cursor.moveToNext()){
            result.num = cursor.getInt(0)
            result.title = cursor.getString(1)
            result.content = cursor.getString(2)
            result.date = cursor.getString(3)
        }
        return result
    }

    // COUNT
    fun getCount() : Int {
        return 0
    }

    // DELETE
    fun deleteMemo(num : Int): Int {
        sqlDB.beginTransaction()
        val result = sqlDB.delete(DATABASE_TABLE,"num=?", arrayOf(num.toString()))
        if(result >0){
            sqlDB.setTransactionSuccessful()
        }
        sqlDB.endTransaction()
        return result
    }


    @Throws(SQLException::class)
    fun open() {
        helper = DBHelper(mCtx!!)
        sqlDB = helper.writableDatabase
    }

    fun dbOpenHelper(context: Context) {
        mCtx = context
    }

    fun create() {
        // DB생성
        helper.onCreate(sqlDB)

    }

    fun upgrade(oldVersion: Int, newVersion: Int) {
        // DB version 변경
        helper.onUpgrade(sqlDB,oldVersion,newVersion)
    }

    fun close() {
        // DB종료
        helper.close()

    }

    inner class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_TABLE, null, 1) {

        override fun onCreate(db: SQLiteDatabase?) { // 테이블 생성
            val query: String =
                "CREATE TABLE if not exists $DATABASE_TABLE ( $NUM integer primary key autoincrement, $MEMO_TITLE text, " +
                        "$MEMO_CONTENT text, $MEMO_DATE text);";
            db!!.execSQL(query)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            val sql: String ="DROP TABLE if exists $DATABASE_TABLE"
            db!!.execSQL(sql)
            onCreate(db)
        }
    }
}