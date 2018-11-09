package com.ticketswap.assessment.entity

import com.google.gson.annotations.SerializedName
import com.ticketswap.assessment.spotify.entity.Entity

class AlbumEntity : Entity {

    @SerializedName("album_type")
    internal var albumType: String? = null
        get
        set

    @SerializedName("artists")
    internal var artists: List<ArtistEntity>? = null
        get
        set
    @SerializedName("available_markets")
    internal var availableMarkets: List<String>? = null
        get
        set
    @SerializedName("external_urls")
    internal var externalUrls: ExternalUrls? = null
        get
        set
    @SerializedName("href")
    internal var href: String? = null
        get
        set
    @SerializedName("id")
    override var id: String? = null

    @SerializedName("images")
    internal var images: List<Image>? = null
        get
        set
    @SerializedName("name")
    internal var name: String? = null
        get
        set

    @SerializedName("type")
    internal var type: String? = null
        get
        set
    @SerializedName("uri")
    internal var uri: String? = null
        get
        set
}
