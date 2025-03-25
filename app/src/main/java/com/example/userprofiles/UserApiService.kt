package com.example.userprofiles

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// code will parse an api request and get the info
// using website random user . something
// gives us a json file back
// if using users and want to display a social media type of thing
// use this

//Do this first

interface UserApiService {
    @GET("api/")
    // this is saying i wanna get something from the url
    // now we want to do something with that data
    // suspend will run it in the background
    suspend fun getRandomUser(): RandomUserResponse
    // it creates this object as best it can using the Gson factory we created

}

// in the api, results is the full api block of the person, in User.kt it has the subblocks
data class RandomUserResponse(@SerializedName("results") val results:List<User> )

// first set up object for interface with the api

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        // generate object based on data given with gson (google json basically)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // the lazy term is used for saying its only got update/pull data when its needed
    // only gonna create this connecction when the call is made
    val api: UserApiService by lazy { retrofit.create(UserApiService::class.java) }
}