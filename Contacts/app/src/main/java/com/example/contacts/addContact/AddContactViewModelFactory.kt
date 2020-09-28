package com.example.contacts.addContact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.database.ContactDatabaseDao
import java.lang.IllegalArgumentException

class AddContactViewModelFactory(val databaseDAO: ContactDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddContactViewModel::class.java)) {
            return AddContactViewModel(databaseDAO) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}