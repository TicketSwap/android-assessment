package com.ticketswap.assessment.domain.model

data class AlbumDomain(
        val albumType: String,
        val artists: List<ArtistDomain>,
        val availableMarkets: List<String>,
        val externalUrls: ExternalUrlsDomain,
        val href: String,
        val id: String,
        val images: List<ImageDomain>,
        val name: String,
        val releaseDate: String,
        val releaseDatePrecision: String,
        val type: String,
        val uri: String
)