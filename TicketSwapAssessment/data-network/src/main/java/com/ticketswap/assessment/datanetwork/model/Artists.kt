package com.ticketswap.assessment.datanetwork.model

import com.squareup.moshi.Json

data class Artists(
        @Json(name = "href") val href: String,
        @Json(name = "items") val items: List<Item>,
        @Json(name = "limit") val limit: Int,
        @Json(name = "next") val next: String?,
        @Json(name = "offset") val offset: Int,
        @Json(name = "previous") val previous: String?,
        @Json(name = "total") val total: Int
)