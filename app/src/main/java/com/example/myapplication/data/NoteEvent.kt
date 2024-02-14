package com.example.myapplication.data

sealed interface NoteEvent {
    data class DeleteNote(val note: Note):NoteEvent

    data class SaveNote(
        val data:String
    ):NoteEvent

    data class UpdateNote(
        val id:Int,
        val data:String,
    ):NoteEvent
}