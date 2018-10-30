package com.ticketswap.assessment.datanetwork.model

import com.squareup.moshi.Json

data class ExternalIds(
        @Json(name = "isrc") val isrc: String
)