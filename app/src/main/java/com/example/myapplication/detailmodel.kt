package com.example.myapplication

import com.google.gson.annotations.SerializedName

class detailmodel {
    // ------------- Getters and Setters -------------
    @SerializedName("id")
    val id: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("rating")
    var rating: Double? = null

    @SerializedName("summary")
    var summary: String? = null

    @SerializedName("cover")
    var cover: Cover? = null

    @SerializedName("genres")
    var genres: List<Genre>? = null

    @SerializedName("platforms")
    var platforms: List<Platform>? = null

    @SerializedName("release_dates")
    var release_dates: List<ReleaseDate>? = null

    @SerializedName("involved_companies")
    var involved_companies: List<InvolvedCompany>? = null

    @SerializedName("similar_games")
    var similar_games: List<SimilarGame>? = null

    @SerializedName("screenshots")
    var screenshots: List<Screenshot>? = null

    // --------- Sub Classes ------------
    class Cover {
        @SerializedName("id")
        val id: String? = null

        @SerializedName("image_id")
        var image_id: String? = null
    }

    inner class SimilarGame {
        // Setters
        // Getters
        var id: Int = 0
        var cover: Cover? = null // Nested object ke liye alag Cover class banegi
        var name: String? = null
    }

    class Genre {
        @SerializedName("name")
        var name: String? = null
    }

    class Platform {
        @SerializedName("name")
        var name: String? = null
    }

    class ReleaseDate {
        @SerializedName("human")
        var human: String? = null
    }

    class InvolvedCompany {
        @SerializedName("company")
        var company: Company? = null

        class Company {
            @SerializedName("name")
            var name: String? = null
        }
    }

    //        public static class SimilarGame {
    //            @SerializedName("name")
    //            private String name;
    //
    //            @SerializedName("cover")
    //            private Cover cover;
    //
    //            public String getName() {
    //                return name;
    //            }
    //
    //            public void setName(String name) {
    //                this.name = name;
    //            }
    //            public Cover getCover() {
    //                return cover;
    //            }
    //
    //            public void setCover(Cover cover) {
    //                this.cover = cover;
    //            }
    //        }
    class Screenshot {
        @SerializedName("image_id")
        var image_id: String? = null
    }
}






