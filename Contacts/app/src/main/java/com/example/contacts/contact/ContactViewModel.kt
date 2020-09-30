package com.example.contacts.contact

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.contacts.database.Contact
import com.example.contacts.database.ContactDatabaseDao
import kotlinx.coroutines.launch

class ContactViewModel(
    private val databaseDAO: ContactDatabaseDao,
    application: Application) : AndroidViewModel(application) {
    val contacts = databaseDAO.getAllContacts()

    fun onClearContacts() {
        viewModelScope.launch {
            clear()
        }
    }
    suspend fun clear() {
        databaseDAO.clear()
    }
}