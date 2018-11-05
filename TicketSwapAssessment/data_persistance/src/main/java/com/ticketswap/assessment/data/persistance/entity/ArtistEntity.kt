package com.ticketswap.assessment.data.persistance.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity
data class ArtistEntity(
        @PrimaryKey
        val id: String,
        val externalUrls: ExternalUrlEntity,
        val genres: List<String>,
        val href: String,
        val images: List<ImageEntity>,
        val name: String,
        val popularity: Int,
        val type: String,
        val uri: String
)

@Entity(foreignKeys = [ForeignKey(entity = ArtistEntity::class, parentColumns = ["id"],
        childColumns = ["artist_id"])])
data class ExternalUrlEntity(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(name = "artist_id") val parentId: String,
        val spotify: String
)

@Entity(foreignKeys = [ForeignKey(entity = ArtistEntity::class, parentColumns = ["id"],
        childColumns = ["artist_id"])])
data class ImageEntity(
        @PrimaryKey(autoGenerate = true) val id: Int,
        @ColumnInfo(name = "parent_id") val parentId: String,
        val height: Int,
        val url: String,
        val width: Int
)