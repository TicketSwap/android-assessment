package com.ticketswap.assessment.datanetwork.model

import com.squareup.moshi.Json

data class Followers(
        @Json(name = "href") val href: Any,
        @Json(name = "total") val total: Int
)