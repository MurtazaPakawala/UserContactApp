package com.example.contactlist

import retrofit2.Call
import retrofit2.http.GET

public interface ContactService {

    @GET("users")
    fun searchContact(): Call<ArrayList<ContactResult>>
}