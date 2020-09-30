package com.example.contacts.database

import androidx.lifecycle.LiveData
import androidx.room.*
import org.json.JSONObject

@Dao
interface ContactDatabaseDao {
    @Insert
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Query("SELECT * from contacts_table WHERE contactID = :key")
    suspend fun get(key: Long): Contact?

    @Query("DELETE FROM contacts_table")
    suspend fun clear()

    @Query("SELECT * FROM contacts_table ORDER BY contact_full_name DESC")
    fun getAllContacts(): LiveData<List<Contact>>
}