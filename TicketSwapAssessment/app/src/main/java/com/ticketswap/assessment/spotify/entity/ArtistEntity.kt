package com.ticketswap.assessment.entity

import com.google.gson.annotations.SerializedName
import com.ticketswap.assessment.spotify.entity.Entity
import com.ticketswap.assessment.spotify.entity.Followers

class ArtistEntity : Entity {

    @SerializedName("id")
    override var id: String? = null

    @SerializedName("genres")
    internal var genres: List<String>? = null
        get
        set
    @SerializedName("images")
    internal var images: List<Image>? = null
        get
        set
    @SerializedName("external_urls")
    internal var externalUrls: ExternalUrls? = null
        get
        set
    @SerializedName("followers")
    internal var followers: Followers? = null
        get
        set
    @SerializedName("href")
    internal var href: String? = null
        get
        set
    @SerializedName("popularity")
    internal var popularity: Int? = null
        get
        set
    @SerializedName("type")
    internal var type: String? = null
        get
        set
    @SerializedName("name")
    internal var name: String? = null
        get
        set
    @SerializedName("uri")
    internal var uri: String? = null
        get
        set
}