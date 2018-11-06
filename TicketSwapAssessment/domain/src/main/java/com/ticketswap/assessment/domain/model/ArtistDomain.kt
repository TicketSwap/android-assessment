package com.ticketswap.assessment.domain.model

data class ArtistDomain (
        val href: String,
        val image: String?,
        val popularity: Int,
        val id: String,
        val name: String,
        val type: String,
        val uri: String
)