package com.derrick.park.assignment3.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.derrick.park.assignment3.repository.ContactsRepository
import com.derrick.park.assignment3.ui.add.AddContactViewModel
import com.derrick.park.assignment3.ui.main.ContactsViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val contactsRepository: ContactsRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(ContactsViewModel::class.java) ->
                    ContactsViewModel(contactsRepository)
                isAssignableFrom(AddContactViewModel::class.java) ->
                    AddContactViewModel(contactsRepository)

                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}