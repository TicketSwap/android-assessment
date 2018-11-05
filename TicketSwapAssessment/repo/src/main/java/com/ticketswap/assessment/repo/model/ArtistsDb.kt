package com.ticketswap.assessment.repo.model

data class ArtistsDb(
        val items: List<ItemDb>
)

data class ItemDb(
        val externalUrls: ExternalUrlDb,
        val genres: List<String>,
        val href: String,
        val id: String,
        val images: List<ImageDb>,
        val name: String,
        val popularity: Int,
        val type: String,
        val uri: String
)

data class ImageDb(
        val height: Int,
        val url: String,
        val width: Int
)

data class ExternalUrlDb(
        val spotify: String
)