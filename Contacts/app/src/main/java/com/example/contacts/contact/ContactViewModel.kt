package com.example.contacts.contact

import android.app.Application
import androidx.lifecycle.*
import com.example.contacts.database.Contact
import com.example.contacts.database.ContactDatabaseDao
import com.example.contacts.model.DomainContact
import com.example.contacts.network.ContactNetwork
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.delay
//import com.example.contacts.network.ContactNetwork
//import com.example.contacts.network.ContactsAPI
import kotlinx.coroutines.launch
import java.io.IOException

enum class ContactsApiStatus { LOADING, ERROR, DONE }

class ContactViewModel(
    private val databaseDAO: ContactDatabaseDao,
    application: Application) : AndroidViewModel(application) {

//    val contacts = databaseDAO.getAllContacts()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private val _status = MutableLiveData<ContactsApiStatus>()

    val status: LiveData<ContactsApiStatus>
        get() = _status

    private val _response = MutableLiveData<List<Contact>>()
    val response: LiveData<List<Contact>>
        get() = _response


    private val _contacts = MutableLiveData<List<DomainContact>>()
    val contactsAPI: LiveData<List<DomainContact>>
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
//                _contacts.value = ContactNetwork.contacts.getContactsAsync(20)
                _status.value = ContactsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ContactsApiStatus.ERROR
            }
        }
    }

    private fun refreshDataFromNetwork() = viewModelScope.launch {

        try {
        } catch (networkError: IOException) {
            delay(2000)
            // Show a Toast error message and hide the progress bar.
            _eventNetworkError.value = true
        }
    }
}