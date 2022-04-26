package com.ssafy.gallery.dto

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Photo")
data class Photo(var location:String, var date: Long, var src: String) : Serializable {

    @PrimaryKey(autoGenerate = true)
    var ID: Long = 0

    constructor(id: Long, location: String, date: Long, src: String) : this(location,date,src){
        this.ID = id
    }
}