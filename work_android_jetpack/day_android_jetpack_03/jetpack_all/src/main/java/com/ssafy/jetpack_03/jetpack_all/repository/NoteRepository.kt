package com.ssafy.jetpack_03.jetpack_all.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.withTransaction
import com.ssafy.jetpack_03.jetpack_all.database.NoteDatabase
import com.ssafy.jetpack_03.jetpack_all.database.NotesDto


private const val DATABASE_NAME = "note-database.db"
class NoteRepository private constructor(context: Context) {

    private val database : NoteDatabase = Room.databaseBuilder(
        context.applicationContext,
        NoteDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val noteDao = database.noteDao()

    fun getNotes(): LiveData<MutableList<NotesDto>> = noteDao.getNotes()

    suspend fun getNote(id: Long): NotesDto =  database.withTransaction {
        noteDao.getNote(id)
    }

    suspend fun insertNote(dto: NotesDto) = database.withTransaction {
        noteDao.insertNote(dto)
    }

    suspend fun updateNote(dto: NotesDto) = database.withTransaction {
        noteDao.updateNote(dto)
    }

    suspend fun deleteNote(dto: NotesDto) = database.withTransaction {
        noteDao.deleteNote(dto)
    }

    companion object {
        private var INSTANCE: NoteRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = NoteRepository(context)
            }
        }

        fun get(): NoteRepository {
            return INSTANCE ?:
            throw IllegalStateException("NoteRepository must be initialized")
        }
    }
}


