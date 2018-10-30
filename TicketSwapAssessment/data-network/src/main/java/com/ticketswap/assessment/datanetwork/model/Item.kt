package com.ticketswap.assessment.datanetwork.model

import com.squareup.moshi.Json

data class Item(
        @Json(name = "external_urls") val externalUrls: ExternalUrls,
        @Json(name = "genres") val genres: List<Any>,
        @Json(name = "href") val href: String,
        @Json(name = "id") val id: String,
        @Json(name = "images") val images: List<Image>,
        @Json(name = "name") val name: String,
        @Json(name = "popularity") val popularity: Int,
        @Json(name = "type") val type: String,
        @Json(name = "uri") val uri: String
)