package com.example.contacts.contact

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.contacts.database.ContactDatabaseDao

class ContactViewModel(
    val databaseDAO: ContactDatabaseDao,
    application: Application) : AndroidViewModel(application) {
}