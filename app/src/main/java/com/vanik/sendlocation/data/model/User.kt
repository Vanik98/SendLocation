package com.vanik.sendlocation.data.model

import android.location.Location

data class User(
    val phoneNumber : String,
    val name :String,
    val surname : String,
    val location: Location
)