package com.example.myapplication.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ContactViewModel(
    private val contactDao: ContactDao
): ViewModel() {

    private var contacts = contactDao.getContactsOrderedByName()

    val _state = MutableStateFlow(ContactState())

    val state = combine(_state,contacts){ state, contacts ->
        state.copy(
            contacts = contacts
        )

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactState())

    fun updateContactDetail(contact: Contact) {
        _state.value = _state.value.copy(detail = contact)
    }

    fun updateVariables(name:String,email:String,phone:String,id:Int) {
        _state.value = _state.value.copy(
            name = mutableStateOf(name),
            email = mutableStateOf(email),
            phone = mutableStateOf(phone),
            id = id
        )
    }
    fun onEvent(event: ContactEvent){
        when (event){
            is ContactEvent.DeleteContact -> {
                viewModelScope.launch {
                    contactDao.deleteContact(event.contact)
                }
            }
            is ContactEvent.SaveContact -> {
                val contact = Contact(
                    state.value.name.value,
                    state.value.email.value,
                    state.value.phone.value,
                    state.value.image.value,
                )
                viewModelScope.launch {
                    contactDao.upsertContact(contact)
                }
                _state.update {
                    it.copy(
                        name = mutableStateOf(""),
                        email = mutableStateOf(""),
                        phone = mutableStateOf("")
                    )
                }
            }
            is ContactEvent.SortContacts -> {
                viewModelScope.launch {
                    contactDao.getContactsOrderedByName()
                }

            }

            is ContactEvent.UpdateContact -> {
                Log.d("detail id---",state.value.id.toString())
                if(state.value.id != null){

                    val contact = Contact(
                        state.value.name.value,
                        state.value.email.value,
                        state.value.phone.value,
                        state.value.image.value,
                        state.value.id!!,
                    )
                    viewModelScope.launch {
                        contactDao.upsertContact(contact)
                    }
                }
            }
        }
    }

}
