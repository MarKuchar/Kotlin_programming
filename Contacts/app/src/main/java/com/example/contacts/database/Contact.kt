package com.example.contacts.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "contacts_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id") var contactID: Long = 0L,

    @ColumnInfo(name = "contact_full_name")
    @Json(name = "first") var fullName: String,

    @ColumnInfo(name = "phone_number")
    @Json(name = "phone") var phoneNumber: Long
)

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