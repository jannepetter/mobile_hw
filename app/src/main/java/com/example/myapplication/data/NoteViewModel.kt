package com.example.myapplication.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel(
    private val noteDao: NoteDao
) :ViewModel() {
    private var notes = noteDao.getNotes()

    val _state = MutableStateFlow(NoteState())

    val state = combine(_state,notes){state,notes ->
        state.copy(
            notes=notes,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),NoteState())

    fun onEvent(event: NoteEvent){

        when(event){
            is NoteEvent.DeleteNote ->{
                viewModelScope.launch {
                    noteDao.deleteNote(event.note)
                }
            }
            is NoteEvent.SaveNote -> {
                viewModelScope.launch {
                    val note = Note(
                        state.value.data.value,
                    )
                    noteDao.upsertNote(
                        note
                    )
                }
            }
            is NoteEvent.UpdateNote->{
                viewModelScope.launch {
                    val note = Note(
                        state.value.data.value,
                        state.value.id!!,
                    )
                    noteDao.upsertNote(
                        note
                    )
                }
            }
        }
    }

}