package com.ssafy.jetpack_03.jetpack_all

import android.app.Application
import com.ssafy.jetpack_03.jetpack_all.repository.NoteRepository


class NoteApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        NoteRepository.initialize(this)
    }
}