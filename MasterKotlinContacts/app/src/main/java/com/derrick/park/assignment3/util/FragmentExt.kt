package com.derrick.park.assignment3.util

import androidx.fragment.app.Fragment
import com.derrick.park.assignment3.ContactsApplication

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as ContactsApplication).contactsRepository
    return ViewModelFactory(repository)
}

