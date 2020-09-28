package com.example.contacts.addContact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts.database.Contact
import com.example.contacts.database.ContactDatabaseDao
import kotlinx.coroutines.launch

class AddContactViewModel(val databaseDAO: ContactDatabaseDao) : ViewModel() {
    private suspend fun insertContact(contact: Contact) {
        databaseDAO.insert(contact)
    }


    fun createContact(fullName: String, phoneNumber: Int) {
        viewModelScope.launch {
            val contact = Contact(fullName = fullName, phoneNumber = phoneNumber)
            insertContact(contact)
        }
    }
}