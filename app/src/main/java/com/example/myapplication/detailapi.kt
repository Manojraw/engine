package com.example.myapplication

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

class detailapi {
    var gameList: List<detailmodel>? = null

    interface ApiService {
        @Headers(
            "Client-ID: yours",
            "Authorization: Bearer <yours>"
        )
        @POST("games")
        fun getGameDetail(@Body query: RequestBody?): Call<List<detailmodel?>?>?
    }


    object Constants {
        const val GAME_DETAIL_QUERY: String =
            "fields  name, cover.image_id, rating, genres.name, summary, platforms.name, release_dates.human, involved_companies.company.name, similar_games.name, similar_games.cover.image_id, screenshots.image_id; where id = "
    }

    companion object {
        private val retrofit: Retrofit? = null
    }
}








