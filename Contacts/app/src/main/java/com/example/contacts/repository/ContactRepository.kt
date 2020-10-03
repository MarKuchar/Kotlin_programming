package com.example.contacts.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.contacts.database.ContactDatabase
import com.example.contacts.database.asDomainModel
import com.example.contacts.model.DomainContact
import com.example.contacts.network.ContactNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import com.example.contacts.network.asDatabaseModel

class ContactRepository(private val database: ContactDatabase) {
    val contacts: LiveData<List<DomainContact>> = Transformations
        .map(database.contactDatabaseDao.getAllContacts()) {
            it.asDomainModel()
        }

    suspend fun refreshContacts() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh contacts is called")
            val contactsAPI = ContactNetwork.contacts.getContactsAsync(10).await()
            database.contactDatabaseDao.insertAll(contactsAPI.asDatabaseModel())
        }
    }
}




