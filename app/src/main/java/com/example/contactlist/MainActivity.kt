package com.example.contactlist

import android.content.Intent
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
        val adapter = ContactAdapter(this,data, object : ContactAdapter.OnClickListener{
            override fun onClick(position: Int) {
                Log.i(TAG,"clciked at position ${position+1}")
                val intent = Intent(this@MainActivity,userInfo::class.java)

                intent.putExtra("name", data[position].name)
                intent.putExtra("email",data[position].email)
                intent.putExtra("phone",data[position].phone)
                intent.putExtra("company_name",data[position].company.name)
                intent.putExtra("address_city",data[position].address.city)
                intent.putExtra("address_suite",data[position].address.suite)
                intent.putExtra("address_street",data[position].address.street)
                intent.putExtra("address_zipcode",data[position].address.zipcode)

                startActivity(intent)
            }

        });
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