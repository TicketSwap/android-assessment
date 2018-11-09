package com.ticketswap.assessment.spotify.auth

import android.util.Base64
import com.ticketswap.assessment.spotify.Constants
import com.ticketswap.assessment.spotify.SpotifyAuthApi
import com.ticketswap.assessment.token.RealmTokenCache
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator: Authenticator {

    var spotifyAuthApi: SpotifyAuthApi? = SpotifyAuthApi()
        set

    var tokenCache: RealmTokenCache? = null
        set

    override fun authenticate(route: Route?, response: Response?): Request {

        val clientId = Constants.API_CLIENT
        val secret = Constants.API_SECRET
        val base64auth = Base64.encodeToString("$clientId:$secret".toByteArray(), Base64.NO_WRAP)
        val authStr = "Basic $base64auth"

        var tokenStr:String? = null

        val responseToken = spotifyAuthApi?.token(authStr,"client_credentials")?.blockingFirst()
        if (responseToken != null) {
            tokenCache?.saveToken(responseToken)
            tokenStr = responseToken.accessToken
        }
        return response?.request()?.newBuilder()!!
                .header(Constants.API_REQUEST_HEADER_AUTH, "Bearer " + tokenStr)
                .build()
    }


}