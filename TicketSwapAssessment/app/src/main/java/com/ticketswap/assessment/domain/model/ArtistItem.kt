package com.ticketswap.assessment.domain.model

data class ArtistItem(val id: String,
                      val image: String?,
                      val name: String,
                      val popularity: Int,
                      val type: String,
                      val uri: String)