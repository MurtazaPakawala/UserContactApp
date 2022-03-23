package com.example.contactlist

data class ContactResult (

    val name : String,
    val email : String,
    val phone : String,
    val website : String,
    val address : getAddress,
    val company : getCompany
        )

data class getCompany (
    val name: String,
        )

data class getAddress (
    val street : String,
    val suite : String,
    val city : String,
    val zipcode : String
        )
