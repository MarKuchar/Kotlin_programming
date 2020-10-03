package com.example.contacts.network

import com.example.contacts.database.Contact
import com.example.contacts.model.DomainContact
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi

@JsonClass(generateAdapter = true)
data class ContactList(
    @field:Json(name="results")
    val contactList: List<ContactApi>)


@JsonClass(generateAdapter = true)
data class ContactApi(val gender: String,
                      val name: Name,
                      val location: Location,
                      val email: String,
                      val cell: String,
                      val login: Login
)

@JsonClass(generateAdapter = true)
data class Name(val first: String, val last: String) {
    val fullName: String
        get() = "$first $last"
}

@JsonClass(generateAdapter = true)
data class Location(val street: Street,
                    val city: String,
                    @field:Json(name="state")
                    val province: String,
                    @field:Json(name="postcode")
                    val postCode: String) {
    val fullAddress: String
        get() = "${street.fullStreet} $city, $province $postCode"
}

@JsonClass(generateAdapter = true)
data class Street(val number: Double, val name: String) {
    val fullStreet: String
        get() = "$number $name"
}

@JsonClass(generateAdapter = true)
data class Login(val uuid: String, val username: String) {
    val toString: String
        get() = uuid
}


/**
 * Converts Network results to domain model
 */
fun ContactList.asDomainModel(): List<DomainContact> {
    return contactList.map {
        DomainContact(
            name = it.name.fullName,
            cell = it.cell
        )
    }
}

/**
 * Converts Network results to database model
 */
fun ContactList.asDatabaseModel(): List<Contact> {
    return contactList.map {
        Contact(
            contactID = 0L,
            fullName = it.name.fullName,
            phoneNumber = it.cell
        )
    }
}
