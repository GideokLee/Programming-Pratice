package com.ssafy.gallery.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ssafy.gallery.dto.Photo

@Database(entities = [Photo::class], version = 1)
abstract class GalleryDatabase : RoomDatabase() {

    abstract fun GalleryDao(): GalleryDao
}