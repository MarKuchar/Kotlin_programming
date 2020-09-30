package com.example.contacts.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://randomuser.me/api/"

// 1. create a retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

//  2. API interface
interface ContactsApiService {
    @GET("contacts")
    fun getProperties():
            Call<String>
}

// 3. Singleton object
object ContactsAPI {
    val retrofitService : ContactsApiService by lazy {
        retrofit.create(ContactsApiService::class.java) }
}