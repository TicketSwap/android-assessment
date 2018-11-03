package com.ticketswap.assessment.view.error

import retrofit2.HttpException

class SpotifyErrorImpl(val ex: Exception) : SpotifyError {
    init {
        if (ex is HttpException) {

        }
    }

    override val message: String
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
}