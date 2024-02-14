package com.example.myapplication.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class NoteState(
    val notes: List<Note> = emptyList(),
    val data: MutableState<String> = mutableStateOf(""),
    val id : Int? = null
)