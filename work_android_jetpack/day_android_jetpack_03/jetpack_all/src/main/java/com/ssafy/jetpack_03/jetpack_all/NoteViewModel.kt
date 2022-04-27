package com.ssafy.jetpack_03.jetpack_all

import androidx.databinding.BaseObservable
import com.ssafy.jetpack_03.jetpack_all.database.NotesDto

class NoteViewModel : BaseObservable() {
    var note : NotesDto? = null
        set(value) {
            field = value
            notifyChange()
        }

    val title : String?
        get() = note?.TITLE
}