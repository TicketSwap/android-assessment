package com.ticketswap.assessment.spotify.entity

import com.google.gson.annotations.SerializedName

class Followers {

    @SerializedName("total")
    var total: Int? = null
    @SerializedName("href")
    var href: Any? = null

}