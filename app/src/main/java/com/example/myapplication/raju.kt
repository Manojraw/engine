package com.example.myapplication

class raju {
    // Getters and Setters
    var id: Int = 0
    var name: String? = null
    var event_logo: EventLogo? = null
    var start_time: Long = 0

    // Constructor
    constructor(id: Int, name: String?, event_logo: EventLogo?, start_time: Long) {
        this.id = id
        this.name = name
        this.event_logo = event_logo
        this.start_time = start_time
    }

    // Empty Constructor (Jarurat padti hai JSON parsing me)
    constructor()

    // Inner Class for Event Logo
    class EventLogo {
        var id: Int = 0
        var image_id: String? = null

        constructor(id: Int, image_id: String?) {
            this.id = id
            this.image_id = image_id
        }

        constructor()
    }
}

