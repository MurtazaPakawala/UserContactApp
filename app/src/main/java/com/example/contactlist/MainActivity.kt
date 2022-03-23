package com.example.contactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val TAG = "MainActivity"
const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val contactService =retrofit.create(ContactService::class.java)
        val contact = contactService.searchContact().enqueue(object : Callback<ArrayList<ContactResult>>{
            override fun onResponse(call: Call<ArrayList<ContactResult>>, response: Response<ArrayList<ContactResult>>) {
                Log.i(TAG,"respong is $response")
            }

            override fun onFailure(call: Call<ArrayList<ContactResult>>, t: Throwable) {
                Log.i(TAG,"error is $t")
            }

        })
    }
}