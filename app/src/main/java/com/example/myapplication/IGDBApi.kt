package com.example.myapplication

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface IGDBApi {
    @Headers(
        "Client-ID: yours",
        "Authorization: Bearer <yours>"
    )
    @POST("games")
    fun getGames(@Body query: RequestBody?): Call<ResponseBody?>?
}
