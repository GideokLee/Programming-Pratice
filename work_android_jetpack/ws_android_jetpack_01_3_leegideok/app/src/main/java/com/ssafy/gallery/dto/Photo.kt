package com.ssafy.gallery.dto

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import java.io.Serializable

data class Photo(var location:String, var date: Long, var src: String) : Serializable {

}