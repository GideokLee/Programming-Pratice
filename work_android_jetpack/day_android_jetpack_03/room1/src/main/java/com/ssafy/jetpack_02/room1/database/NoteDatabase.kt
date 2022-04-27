package com.ssafy.jetpack_02.room1.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NotesDto::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

}


