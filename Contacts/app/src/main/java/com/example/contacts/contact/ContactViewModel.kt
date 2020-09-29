package com.example.contacts.contact

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts.database.Contact
import com.example.contacts.database.ContactDatabaseDao
import kotlinx.coroutines.launch

class ContactViewModel(
    private val databaseDAO: ContactDatabaseDao,
    application: Application) : AndroidViewModel(application) {
    val contacts = databaseDAO.getAllContacts()

    fun clearContacts() {
        viewModelScope.launch {
            databaseDAO.clear()
        }
    }
}