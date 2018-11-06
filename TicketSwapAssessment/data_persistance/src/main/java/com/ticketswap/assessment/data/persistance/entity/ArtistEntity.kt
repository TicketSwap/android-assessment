package com.ticketswap.assessment.data.persistance.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class ArtistEntity(
        @PrimaryKey
        var id: String = "",
        var href: String = "",
        var image: String? = null,
        var name: String = "",
        var popularity: Int = 0,
        var type: String = "",
        var uri: String = ""
)