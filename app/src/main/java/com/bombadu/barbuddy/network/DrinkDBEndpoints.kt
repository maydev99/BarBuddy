package com.bombadu.barbuddy.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinkDBEndpoints {

    @GET("api/json/v1/1/search.php")
    fun getDrinks(
        @Query("s") search: String
    ) : Call<DrinkData>
}