package com.ssafy.jetpack_02.room1.database

import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    suspend fun getNotes(): MutableList<NotesDto>

    @Query("SELECT * FROM note WHERE ID = (:id)")
    suspend fun getNote(id: Long): NotesDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(dto: NotesDto)

    @Update
    suspend fun updateNote(dto: NotesDto)

    @Delete
    suspend fun deleteNote(dto: NotesDto)
}


