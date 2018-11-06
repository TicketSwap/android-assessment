package com.ticketswap.assessment.repo.model

data class ItemDb(
        val id: String,
        val image: List<ImageDb>,
        val name: String,
        val popularity: Int,
        val type: String,
        val uri: String
)

data class ImageDb(
        val height: Int,
        val url: String?,
        val width: Int
)