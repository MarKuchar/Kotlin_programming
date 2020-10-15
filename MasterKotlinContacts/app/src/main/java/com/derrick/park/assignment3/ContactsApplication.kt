package com.derrick.park.assignment3

import android.app.Application
import com.derrick.park.assignment3.database.getDatabase
import com.derrick.park.assignment3.repository.ContactsRepository
import timber.log.Timber

class ContactsApplication : Application() {

    val contactsRepository: ContactsRepository
        get() = ContactsRepository(getDatabase(this))


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}