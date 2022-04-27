package com.ssafy.jetpack_03.jetpack_all

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ssafy.jetpack_03.jetpack_all.database.NotesDto
import com.ssafy.jetpack_03.jetpack_all.repository.NoteRepository

class NoteListViewModel : ViewModel() {
    private val noteRepository: NoteRepository = NoteRepository.get()
    val noteList: LiveData<MutableList<NotesDto>> = noteRepository.getNotes()
}