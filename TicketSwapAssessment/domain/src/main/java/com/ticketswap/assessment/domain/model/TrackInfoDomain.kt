package com.ticketswap.assessment.domain.model

data class TrackInfoDomain(
        val album: AlbumDomain,
        val artists: List<ArtistDomain>,
        val availableMarkets: List<String>,
        val discNumber: Int,
        val durationMs: Int,
        val explicit: Boolean,
        val externalIds: ExternalIdsDomain,
        val externalUrls: ExternalUrlsDomain,
        val href: String,
        val id: String,
        val isLocal: Boolean,
        val name: String,
        val popularity: Int,
        val previewUrl: String,
        val trackNumber: Int,
        val type: String,
        val uri: String
)