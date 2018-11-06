package com.ticketswap.assessment.domain.model

data class SpotifyError(val error: Error?)

data class Error(val status: Int, val message: String?)