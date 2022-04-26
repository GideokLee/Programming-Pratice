package com.ssafy.gallery

import androidx.lifecycle.ViewModel
import com.ssafy.gallery.dto.Photo

class PhotoViewModel : ViewModel() {
    lateinit var currPhoto : Photo

    fun setPhoto(photo: Photo){
        this.currPhoto = photo
    }

    fun getPhoto() = currPhoto

}