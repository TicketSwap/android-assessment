package com.ticketswap.assessment.spotify

interface SpotifyLogin {
    fun requestLoginActivity()
    fun extractData(): SpotifyLoginResult?
}