package com.example.contacts.contact

import android.app.Application
import androidx.lifecycle.*
import com.example.contacts.database.Contact
import com.example.contacts.database.ContactDatabase
import com.example.contacts.database.ContactDatabaseDao
import com.example.contacts.model.DomainContact
import com.example.contacts.repository.ContactRepository
//import com.example.contacts.network.ContactNetwork
//import com.example.contacts.network.ContactsAPI
import kotlinx.coroutines.launch
import java.io.IOException

enum class ContactsApiStatus { LOADING, ERROR, DONE }

class ContactViewModel(
    private val databaseDAO: ContactDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    private val contactRepository = ContactRepository(ContactDatabase.getInstance(application))

    val contacts = contactRepository.contacts

//    val contacts = databaseDAO.getAllContacts()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    init {
        refreshDataFromRepository()
    }

    fun onClearContacts() {
        viewModelScope.launch {
            clear()
        }
    }
    private suspend fun clear() {
        databaseDAO.clear()
    }

    /**
     * Refresh data from the repository. Use a coroutine launch to run in a
     * background thread.
     */
    fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                contactRepository.refreshContacts()
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
                if(contacts.value.isNullOrEmpty())
                    _eventNetworkError.value = true
            }
        }
    }

    /**
     * Resets the network error flag.
     */
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
}