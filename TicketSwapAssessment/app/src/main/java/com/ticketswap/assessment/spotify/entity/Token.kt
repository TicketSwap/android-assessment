package com.ticketswap.assessment.spotify.entity

import com.google.gson.annotations.SerializedName


data class Token (

    @SerializedName("access_token")
    var accessToken: String?,

    @SerializedName("token_type")
    var tokenType: String?,

    @SerializedName("expires_in")
    var expires: Long,

    override val id: String?): Entity {

}