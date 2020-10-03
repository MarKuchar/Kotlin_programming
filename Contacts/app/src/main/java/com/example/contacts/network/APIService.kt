package com.example.contacts.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://randomuser.me/api/"

/**
 * A retrofit service to fetch a contact list.
 */
interface ContactApiService {
    @GET("?nat=us")
    fun getContactsAsync(@Query("results") num: Int): Deferred<ContactList>
}

/**
 * Main entry point for network access. Call like `ContactNetwork.contacts.getContactsAsync()`
 */
object ContactNetwork {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    val contacts: ContactApiService = retrofit.create(ContactApiService::class.java)
}


