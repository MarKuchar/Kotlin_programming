package com.example.contacts.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDatabaseDao {
    @Insert
    suspend fun insert(contact: Contact)

    @Query("DELETE FROM contacts_table")
    suspend fun clear()

    @Query("SELECT * FROM contacts_table ORDER BY contact_full_name DESC")
    fun getAllContacts(): LiveData<List<Contact>>
}