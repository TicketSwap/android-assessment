package com.ticketswap.assessment.data.persistance.entity

import android.arch.persistence.room.*

@Entity
data class ArtistEntity(
        @PrimaryKey
        var id: String = "",
        @Ignore
        val externalUrls: ExternalUrlEntity? = null,
        @Ignore
        val genres: List<String> = listOf(),
        var href: String = "",
        @Ignore
        val images: List<ImageEntity> = listOf(),
        var name: String = "",
        var popularity: Int = 0,
        var type: String = "",
        var uri: String = ""
)

@Entity(foreignKeys = [ForeignKey(entity = ArtistEntity::class, parentColumns = ["id"],
        childColumns = ["artist_id"])])
data class ExternalUrlEntity(
        @PrimaryKey(autoGenerate = true) var id: Int,
        @ColumnInfo(name = "artist_id") var parentId: String = "",
        var spotify: String = ""
)

@Entity(foreignKeys = [ForeignKey(entity = ArtistEntity::class, parentColumns = ["id"],
        childColumns = ["artist_id"])])
data class ImageEntity(
        @PrimaryKey(autoGenerate = true) var id: Int,
        @ColumnInfo(name = "artist_id") var parentId: String = "",
        var height: Int = 0,
        var url: String = "",
        var width: Int = 0
)