package com.ssafy.gallery

import android.content.Context
import androidx.room.Room
import androidx.room.withTransaction
import com.ssafy.gallery.database.GalleryDatabase
import com.ssafy.gallery.dto.Photo
import java.lang.IllegalStateException

private const val DATABASE_NAME = "gallery-database"
class GalleryRepository private constructor(context: Context){

    private val database : GalleryDatabase = Room.databaseBuilder(
        context.applicationContext,
        GalleryDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val galleryDao = database.GalleryDao()

    suspend fun getPhotos() : MutableList<Photo> = database.withTransaction {
        galleryDao.getPhotos()
    }

    companion object{
        private var INSTANCE : GalleryRepository? = null
        fun initialize(context: Context){
            if(INSTANCE == null){
                INSTANCE = GalleryRepository(context)
            }
        }

        fun get(): GalleryRepository{
            return INSTANCE ?:
            throw IllegalStateException("GalleryRepository must be initialized")
        }
    }
}