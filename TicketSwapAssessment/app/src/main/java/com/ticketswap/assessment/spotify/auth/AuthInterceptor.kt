package com.ticketswap.assessment.spotify.auth

import com.ticketswap.assessment.spotify.Constants
import com.ticketswap.assessment.spotify.entity.Token
import com.ticketswap.assessment.token.RealmTokenCache
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    var  tokenCache: RealmTokenCache? = null
        set

    override fun intercept(chain: Interceptor.Chain?): Response {

        var request = chain?.request()

        var token: Token? = tokenCache?.getToken()
        if (token != null) {
            request = chain?.request()?.newBuilder()?.addHeader(Constants.API_REQUEST_HEADER_AUTH, "Bearer " + token.accessToken)?.build()
        }

        return chain!!.proceed(request)
    }
}