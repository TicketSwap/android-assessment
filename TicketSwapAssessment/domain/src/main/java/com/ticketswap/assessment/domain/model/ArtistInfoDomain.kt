package com.ticketswap.assessment.domain.model

data class ArtistInfoDomain(
        val externalUrls: ExternalUrlsDomain,
        val followers: FollowersDomain,
        val genres: List<String>,
        val href: String,
        val id: String,
        val images: List<ImageDomain>,
        val name: String,
        val popularity: Int,
        val type: String,
        val uri: String
)