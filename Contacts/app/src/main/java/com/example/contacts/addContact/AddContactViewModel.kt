package com.example.contacts.addContact

import androidx.lifecycle.*
import com.example.contacts.PairLiveData
import com.example.contacts.database.Contact
import com.example.contacts.database.ContactDatabaseDao
import kotlinx.coroutines.launch

class AddContactViewModel(private val databaseDAO: ContactDatabaseDao) : ViewModel() {

    var hasFullName = MutableLiveData<Boolean>(false)
    var hasPhoneNumber = MutableLiveData<Boolean>(false)

    private var newContact = MutableLiveData<Contact?>()

    private suspend fun insertContact(contact: Contact) {
        databaseDAO.insert(contact)
    }

    fun createContact(fullName: String, phoneNumber: Int) {
        viewModelScope.launch {
            val contact = Contact(fullName = fullName, phoneNumber = phoneNumber)
            insertContact(contact)
        }
    }

    val saveButtonVisible = Transformations.map(hasFullName.combine(hasPhoneNumber)) {
        it.first!! && it.second!!
    }

    fun <A, B> MutableLiveData<A>.combine(other: MutableLiveData<B>): PairLiveData<A, B> {
        return PairLiveData(this, other)
    }
}
