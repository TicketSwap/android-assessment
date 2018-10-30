package com.ticketswap.assessment.domain.model

data class ArtistsDomain(
        val href: String,
        val items: List<ItemDomain>,
        val limit: Int,
        val next: Any,
        val offset: Int,
        val previous: Any,
        val total: Int
)