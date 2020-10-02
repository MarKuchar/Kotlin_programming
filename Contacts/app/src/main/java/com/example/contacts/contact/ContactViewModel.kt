package com.example.contacts.contact

import android.app.Application
import androidx.lifecycle.*
import com.example.contacts.database.Contact
import com.example.contacts.database.ContactDatabaseDao
import com.example.contacts.database.ContactList
import com.example.contacts.network.ContactsAPI
import kotlinx.coroutines.Deferred
//import com.example.contacts.network.ContactNetwork
//import com.example.contacts.network.ContactsAPI
import kotlinx.coroutines.launch

enum class ContactsApiStatus { LOADING, ERROR, DONE }

class ContactViewModel(
    private val databaseDAO: ContactDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    val contacts = databaseDAO.getAllContacts()

    private val _status = MutableLiveData<ContactsApiStatus>()

    val status: LiveData<ContactsApiStatus>
        get() = _status

    private val _response = MutableLiveData<List<Contact>>()
    val response: LiveData<List<Contact>>
        get() = _response


    private val _contacts = MutableLiveData<Deferred<ContactList>>()
    val contactsAPI: LiveData<Deferred<ContactList>>
        get() = _contacts

    init {
        getContacts()
    }

    fun onClearContacts() {
        viewModelScope.launch {
            clear()
        }
    }
    private suspend fun clear() {
        databaseDAO.clear()
    }

    private fun getContacts() {
        viewModelScope.launch {
            _status.value = ContactsApiStatus.LOADING
            try {
                _contacts.value = ContactsAPI.retrofitService.getContactsAsync(10)
                _status.value = ContactsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ContactsApiStatus.ERROR
            }
        }
    }
}