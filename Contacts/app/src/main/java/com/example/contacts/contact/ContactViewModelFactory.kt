package com.example.contacts.contact

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.database.ContactDatabaseDao
import java.lang.IllegalArgumentException

class ContactViewModelFactory(
    private val dataSource: ContactDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            return ContactViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}
