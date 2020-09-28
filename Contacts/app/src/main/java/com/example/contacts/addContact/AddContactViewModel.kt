package com.example.contacts.addContact

import androidx.lifecycle.ViewModel
import com.example.contacts.database.ContactDatabaseDao

class AddContactViewModel(val databaseDAO: ContactDatabaseDao) : ViewModel()