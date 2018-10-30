package com.ticketswap.assessment.datanetwork.model

import com.squareup.moshi.Json

data class Image(
        @Json(name = "height") val height: Int,
        @Json(name = "url") val url: String,
        @Json(name = "width") val width: Int
)