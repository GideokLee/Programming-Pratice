package com.ssafy.gallery.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ssafy.gallery.Photo

@Dao
interface GalleryDao {

    @Query("SELECT * FROM photo")
    suspend fun getPhotos(): List<Photo>

    @Query("SELECT * FROM photo WHERE id = (:id)")
    suspend fun getPhoto(id: Long): Photo

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Photo)

    @Query("SELECT count(photo.id) FROM photo")
    suspend fun getCount() : Int
}