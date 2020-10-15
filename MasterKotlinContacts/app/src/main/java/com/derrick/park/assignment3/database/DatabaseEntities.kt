package com.derrick.park.assignment3.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.derrick.park.assignment3.domain.Contact
import java.util.*


@Entity(tableName = "contacts")
data class ContactEntity constructor(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    var gender: String?,
    var name: String,
    var location: String?,
    var cell: String,
    var email: String?)

fun List<ContactEntity>.asDomainModel(): List<Contact> {
    return map {
        Contact(
            id = it.id,
            gender = it.gender,
            name = it.name,
            location = it.location,
            cell = it.cell,
            email = it.email
        )
    }
}


