package com.ssafy.content.provider.note

import android.content.*
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.net.Uri
import android.text.TextUtils
import android.util.Log


private const val TAG = "NotesDbHelper_싸피"
class NotesDbHelper : ContentProvider() {

    lateinit var mDbHelper: DatabaseHelper
    lateinit var mDb: SQLiteDatabase

    companion object {
        //  getType에서 사용함. 일반적으로 vnd+authoroty+dir or item
        val CONTENT_TYPE = "vnd.com.ssafy.content.provider.note.note.dir/" + Notes.AUTHORITY
        val CONTENT_ITEM_TYPE = "vnd.com.ssafy.content.provider.note.note.item/" + Notes.AUTHORITY

        private val NOTES = 1
        private val NOTE_ID = 2

        private const val DATABASE_CREATE =
            ("create table notes (_id integer primary key autoincrement, title text not null, body text not null);")

        private const val DATABASE_NAME = "data"
        private const val DATABASE_TABLE = "notes"
        private const val DATABASE_VERSION = 1
    }

    private var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply{
        addURI(Notes.AUTHORITY, "notes", NOTES)
        addURI(Notes.AUTHORITY, "notes/#", NOTE_ID)
    }

    class DatabaseHelper (context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        override fun onCreate(db: SQLiteDatabase) {
            Log.d(TAG, "NotesDbHelper.DatabaseHelper.onCreate")
            db.execSQL(DATABASE_CREATE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            Log.w( TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data")
            db.execSQL("DROP TABLE IF EXISTS notes")
            onCreate(db)
        }
    }

    fun close() {
        mDbHelper.close()
    }

    override fun onCreate(): Boolean {
        Log.d(TAG, "NotesDbHelper.onCreate..")
        mDbHelper = DatabaseHelper(context)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?,
        sortOrder: String? ): Cursor? {
        val qb = SQLiteQueryBuilder()
        when (sUriMatcher.match(uri)) {
            NOTES -> qb.tables = DATABASE_TABLE
            NOTE_ID -> { qb.tables = DATABASE_TABLE
                qb.appendWhere(Notes.ID + "=" + uri.pathSegments[1])
            }
            else -> throw java.lang.IllegalArgumentException("Unknown URI $uri")
        }

        val orderBy: String
        orderBy = if (TextUtils.isEmpty(sortOrder)) {
            Notes.DEFAULT_SORT_ORDER
        } else {
            sortOrder!!
        }

        val db: SQLiteDatabase = mDbHelper.readableDatabase

        val c = qb.query(db, projection, selection, selectionArgs, null, null, orderBy)
        c.setNotificationUri(context!!.contentResolver, uri)

        return c
    }

    override fun getType(uri: Uri): String? {
        return when (sUriMatcher.match(uri)) {
            NOTES -> CONTENT_TYPE
            NOTE_ID -> CONTENT_ITEM_TYPE
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }

    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        //require : 인자값이 참인지 확인. false라면  IllegalArgumentException 발생
        require(sUriMatcher.match(uri) == NotesDbHelper.NOTES) { "Unknown URI $uri" }

        val db: SQLiteDatabase = mDbHelper.writableDatabase
        val rowId = db.insert(NotesDbHelper.DATABASE_TABLE, null, values)

        val insertedUri = ContentUris.withAppendedId(uri, rowId)
        context!!.contentResolver.notifyChange(insertedUri, null)

        if (rowId > 0) {
            return insertedUri
        }

        throw SQLException("Failed to insert row into $uri")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return when (sUriMatcher.match(uri)) {
            NOTES -> {
                throw java.lang.IllegalArgumentException("Invalid URI, cannot update without ID: $uri")
            }
            NOTE_ID -> {
                val id = ContentUris.parseId(uri)
                val count = mDbHelper.writableDatabase
                    .delete(DATABASE_TABLE,
                        "${Notes.ID} = ?",
                        arrayOf(id.toString()))
                context!!.contentResolver.notifyChange(uri, null)
                count
            }
            else -> {
                throw java.lang.IllegalArgumentException("Unknown URI: $uri")
            }
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?
    ): Int {
        Log.d(TAG, "Update called...${uri}")
        return when (sUriMatcher.match(uri)) {
            NOTES -> {
                throw java.lang.IllegalArgumentException("Invalid URI, cannot update without ID$uri")
            }
            NOTE_ID -> {
                val id = ContentUris.parseId(uri)
                val count = mDbHelper.writableDatabase
                    .update(
                        DATABASE_TABLE, values, "${Notes.ID} = ?",
                        arrayOf(id.toString())
                    )
                context!!.contentResolver.notifyChange(uri, null)
                count
            }
            else -> {
                throw java.lang.IllegalArgumentException("Unknown URI: $uri")
            }
        }
    }
}