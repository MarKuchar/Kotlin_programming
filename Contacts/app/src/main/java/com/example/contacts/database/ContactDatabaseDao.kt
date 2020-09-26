package com.example.contacts.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDatabaseDao {

    @Insert
    fun insert(contact: Contact)

    @Update
    fun update(contact: Contact)

    @Query("SELECT * from contacts_table WHERE contactID = :key")
    fun get(key: Long): Contact?

    @Query("DELETE FROM contacts_table")
    fun clear()

    @Query("SELECT * FROM contacts_table ORDER BY contact_full_name DESC")
    fun getAllContacts(): LiveData<List<Contact>>
}