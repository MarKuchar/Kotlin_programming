package com.example.contacts.network

import com.example.contacts.database.Contact
import com.example.contacts.database.ContactList
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

//import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://randomuser.me/api/"


// Create moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// 1. Create a retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//  2. API interface
interface ContactApiService {
    @GET("?nat=ca")
    fun getContactsAsync(@Query("results") num: Int): Deferred<ContactList>
}

// 3. Singleton object
object ContactsAPI {
    val retrofitService : ContactApiService by lazy {
        retrofit.create(ContactApiService::class.java) }
}

object ContactNetwork {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
    val contacts: ContactApiService = retrofit.create(ContactApiService::class.java)
}
