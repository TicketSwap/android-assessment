package com.ticketswap.assessment.domain.model

data class ItemDomain(
        val externalUrls: ExternalUrlsDomain?,
        val genres: List<Any>,
        val href: String,
        val id: String,
        val images: List<ImageDomain>,
        val name: String,
        val popularity: Int,
        val type: String,
        val uri: String
)