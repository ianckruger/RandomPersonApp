package com.example.userprofiles


import com.google.gson.annotations.SerializedName

//do this third

data class User(
    @SerializedName("name") val name: Name,
    @SerializedName("picture") val picture: Picture
)


// getting the first and last name from api pull
data class Name(
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String
)

data class Picture(
    // want the 'large' portion of the picture api
    @SerializedName("large") val large: String
)