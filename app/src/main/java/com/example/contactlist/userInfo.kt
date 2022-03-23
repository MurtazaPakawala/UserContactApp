package com.example.contactlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_info.*

class userInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val name = intent.getStringExtra("name")

        tvNameHead.text = name
        tvPhone.text =intent.getStringExtra("phone")
        tvEmail.text = intent.getStringExtra("email")
        tvAdress.text="${intent.getStringExtra("address_street")} " +
                "\n ${intent.getStringExtra("address_suite")} " +
                "\n ${intent.getStringExtra("address_city")} "+
                "\n ${intent.getStringExtra("address_zipcode")} "


        tvBuisness.text = intent.getStringExtra("company_name")
    }
}