//package com.example.contacts.network
//
//import com.example.contacts.database.Contact
//import retrofit2.Call
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.http.GET
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//
//
//private const val BASE_URL = "https://randomuser.me/api/?inc=name,phone"
//
//
//// Create moshi object
//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
//// 1. Create a retrofit object
//private val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .baseUrl(BASE_URL)
//    .build()
//
////  2. API interface
//interface ContactsApiService {
//    @GET("contacts")
//    fun getProperties():
//            Call<List<Contact>>
//}
//
//// 3. Singleton object
//object ContactsAPI {
//    val retrofitService : ContactsApiService by lazy {
//        retrofit.create(ContactsApiService::class.java) }
//}