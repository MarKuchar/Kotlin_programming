package com.example.contacts.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.contacts.database.Contact
import com.example.contacts.database.ContactDatabase
import com.example.contacts.database.asDomainModel
import com.example.contacts.model.DomainContact

class ContactRepository(private val database: ContactDatabase) {
    val contacts: LiveData<List<DomainContact>> =
        Transformations.map(database.contactDatabaseDao.getAllContacts()){
        it.asDomainModel()
        }

}

//class VideosRepository(private val database: VideosDatabase){
//
//        val videos:LiveData<List<DevByteVideo>>=Transformations.map(database.videoDao.getVideos()){
//        it.asDomainModel()
//        }
//
//        /**
//         * Refresh the videos stored in the offline cache.
//         *
//         * This function uses the IO dispatcher to ensure the database insert database operation
//         * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
//         * function is now safe to call from any thread including the Main thread.
//         *
//         */
//        suspend fun refreshVideos(){
//        withContext(Dispatchers.IO){
//        Timber.d("refresh videos is called");
//        val playlist=DevByteNetwork.devbytes.getPlaylist()
//        database.videoDao.insertAll(playlist.asDatabaseModel())
//        }
//        }
//
//