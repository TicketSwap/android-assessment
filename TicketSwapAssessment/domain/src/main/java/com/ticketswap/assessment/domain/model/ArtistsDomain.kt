package com.ticketswap.assessment.domain.model

data class ArtistsDomain(
        val href: String,
        val items: List<ItemDomain>,
        val limit: Int,
        val next: String?,
        val offset: Int,
        val previous: String?,
        val total: Int
)