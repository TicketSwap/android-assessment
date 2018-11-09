package com.ticketswap.assessment.entity

import com.google.gson.annotations.SerializedName

data class Image (
    @SerializedName("url")
    var url: String?,
    @SerializedName("width")
    var width: Int?,
    @SerializedName("height")
    var height: Int? ) {
}