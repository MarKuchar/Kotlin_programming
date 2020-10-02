package com.example.contacts.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

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
                      val login: Login)

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
//
///**
// * Converts Network results to domain model
// */
//fun ContactList.asDomainModel(): List<Contact> {
//    return contactList.map {
//        Contact(
//            contactID = it.login.toString,
//            fullName = it.name.fullName,
//            email = it.email,
//            cell = it.cell
//        )
//    }
//}
//
///**
// * Converts Network results to database model
// */
//fun ContactList.asDatabaseModel(): List<ContactEntity> {
//    return contactList.map {
//        ContactEntity(
//            id = it.login.toString,
//            gender = it.gender,
//            name = it.name.fullName,
//            location = it.location.fullAddress,
//            email = it.email,
//            cell = it.cell
//        )
//    }
//}