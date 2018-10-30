package com.ticketswap.assessment.datanetwork.model

import com.squareup.moshi.Json

data class SearchResponse(
        @Json(name = "artists") val artists: Artists
)