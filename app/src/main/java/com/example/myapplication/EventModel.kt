package com.example.myapplication

class EventModel {
    // Getters
    val id: Int = 0
    val name: String? = null
    val description: String? = null
    val event_logo: EventLogo? = null
    val start_time: Long = 0
    val games: List<GameModel>? = null

    // Inner Class for Event Logo
    class EventLogo {
        val id: Int = 0
        val image_id: String? = null
    }

    // Inner Class for Game
    class Game {
        val cover: Cover? = null
        val name: String? = null
    }
}
