package com.ssafy.jetpack_02.room1

import android.app.Application
import com.ssafy.jetpack_02.room1.repository.NoteRepository


class NoteApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        NoteRepository.initialize(this)
    }
}