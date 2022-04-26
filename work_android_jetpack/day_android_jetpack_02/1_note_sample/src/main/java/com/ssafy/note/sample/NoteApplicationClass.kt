package com.ssafy.note.sample

import android.app.Application
import com.ssafy.note.sample.repository.NoteRepository


class NoteApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        NoteRepository.initialize(this)
    }
}