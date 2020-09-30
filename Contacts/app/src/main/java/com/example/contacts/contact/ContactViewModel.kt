package com.example.contacts.contact

import android.accounts.NetworkErrorException
import android.app.Application
import androidx.lifecycle.*
import com.example.contacts.database.Contact
import com.example.contacts.database.ContactDatabaseDao
//import com.example.contacts.network.ContactNetwork
//import com.example.contacts.network.ContactsAPI
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactViewModel(
    private val databaseDAO: ContactDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    val contacts = databaseDAO.getAllContacts()

//    private val _response = MutableLiveData<List<Contact>>()
//    val response: LiveData<List<Contact>>
//        get() = _response

//    init {
//        getContacts()
//    }

    fun onClearContacts() {
        viewModelScope.launch {
            clear()
        }
    }
    suspend fun clear() {
        databaseDAO.clear()
    }

//    private fun getContacts() {
//        ContactsAPI.retrofitService.getProperties().enqueue(
//            object: Callback<List<Contact>> {
//                override fun onFailure(call: Call<List<Contact>>, t: Throwable) {
//                    throw NetworkErrorException("The failure message: " + t.message)
//                }
//
//                override fun onResponse(call: Call<List<Contact>>,
//                                        response: Response<List<Contact>>
//                ) {
//                    _response.value = response.body()
//                }
//            })
//    }
}