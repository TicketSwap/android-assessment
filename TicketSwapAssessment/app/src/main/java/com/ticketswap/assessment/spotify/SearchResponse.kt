package com.ticketswap.assessment.spotify

import com.google.gson.annotations.SerializedName
import com.ticketswap.assessment.entity.Albums
import com.ticketswap.assessment.entity.Artists
import com.ticketswap.assessment.entity.Tracks

class SearchResponse {

    @SerializedName("artists")
    internal var artists: Artists? = null
        get
        set
    @SerializedName("albums")
    internal var albums: Albums? = null
        get
        set
    @SerializedName("tracks")
    internal var tracks: Tracks? = null
        get
        set
}