package com.example.myapplication.data

sealed interface ContactEvent {
    object SortContacts: ContactEvent

    data class DeleteContact(val contact: Contact): ContactEvent

    data class SaveContact(
        val name:String,
        val email:String,
        val phone:String,
        val image:Int
    ): ContactEvent

    data class UpdateContact(
        val id: Int,
        val name:String,
        val email:String,
        val phone:String
    ): ContactEvent
}