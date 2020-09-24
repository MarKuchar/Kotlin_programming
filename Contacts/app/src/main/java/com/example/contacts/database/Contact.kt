package com.example.contacts.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var contactID: Long = 0L,

    @ColumnInfo(name = "contact_full_name")
    var fullName: String,

    @ColumnInfo(name = "phone_number")
    var phoneNumber: Int
)
