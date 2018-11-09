package com.ticketswap.assessment.token

import com.ticketswap.assessment.spotify.entity.Token


interface TokenCache {
    fun getToken(): Token?
    fun saveToken(token: Token)
}