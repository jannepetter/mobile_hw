package com.example.myapplication.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf

data class ContactState(
    val contacts: List<Contact> = emptyList(),
    val name: MutableState<String> = mutableStateOf(""),
    val email: MutableState<String> = mutableStateOf(""),
    val phone: MutableState<String> = mutableStateOf(""),
    val image: MutableState<Int> = mutableIntStateOf(0),
    val detail: Contact? = null,
    val id : Int? = null
)