package com.example.myapplication

import com.google.gson.annotations.SerializedName

class GameModel(// Getters
    @field:SerializedName("id") val id: String?, @field:SerializedName(
        "name"
    ) val name: String?, imageId: String?
) {
    @SerializedName("cover")
    private val cover: Cover

    init {
        cover = Cover()
        cover.imageId = imageId
    }

    class Cover {
        // Getters
        @SerializedName("id")
        val id: String? = null

        @SerializedName("image_id")
        var imageId: String? = null
    }

    fun getCover(): Cover? {
        return cover
    }
}