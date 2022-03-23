package com.example.contactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
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
        //dragged the recycler view
        // making the layout
        rvcontact.layoutManager = LinearLayoutManager(this);
        //making the data
        val data = mutableListOf<ContactResult>()
        //making the adapter
        val adapter = ContactAdapter(this,data);
        rvcontact.adapter = adapter


        val retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val contactService =retrofit.create(ContactService::class.java)
        val contact = contactService.searchContact().enqueue(object : Callback<ArrayList<ContactResult>>{
            override fun onResponse(call: Call<ArrayList<ContactResult>>, response: Response<ArrayList<ContactResult>>) {
                Log.i(TAG,"respong is $response")
                val body = response.body()
                if(body == null)
                {
                    Log.w(TAG,"Did not recieve the valid info");
                }
                if (body != null) {
                    data.addAll(body)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<ContactResult>>, t: Throwable) {
                Log.i(TAG,"error is $t")
            }

        })
    }
}