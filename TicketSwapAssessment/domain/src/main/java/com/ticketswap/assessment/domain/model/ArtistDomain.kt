package com.ticketswap.assessment.domain.model

data class ArtistDomain (
        val externalUrls: ExternalUrlsDomain,
        val href: String,
        val id: String,
        val name: String,
        val type: String,
        val uri: String
)