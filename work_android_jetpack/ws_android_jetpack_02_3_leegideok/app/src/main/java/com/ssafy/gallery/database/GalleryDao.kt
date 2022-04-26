package com.ssafy.gallery.database

import androidx.room.Dao
import androidx.room.Query
import com.ssafy.gallery.dto.Photo


@Dao
interface GalleryDao {

    @Query("SELECT * FROM photo")
    suspend fun getPhotos(): MutableList<Photo>


}