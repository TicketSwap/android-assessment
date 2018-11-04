package com.ticketswap.assessment.repo.model

data class UserInfoRequest(private val accessToken: String?) {
    val token: String?
        get() = accessToken?.let { "Bearer $it" }
}