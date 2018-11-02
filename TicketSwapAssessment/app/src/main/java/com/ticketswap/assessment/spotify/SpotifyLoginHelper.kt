package com.ticketswap.assessment.spotify

import android.app.Activity
import android.content.Intent
import android.net.Uri
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.ticketswap.assessment.R
import javax.inject.Inject

class SpotifyLoginHelper @Inject constructor(val activity: Activity, val requestCode: Int, val resultCode: Int, val data: Intent?) : SpotifyLogin {

    override fun requestLoginActivity() {
        AuthenticationClient.openLoginActivity(activity, com.ticketswap.assessment.SpotifyLogin.CODE,
                AuthenticationRequest.Builder(
                        com.ticketswap.assessment.SpotifyLogin.CLIENT_ID,
                        AuthenticationResponse.Type.TOKEN, Uri.Builder()
                        .scheme(activity.getString(R.string.com_spotify_sdk_redirect_scheme))
                        .authority(activity.getString(R.string.com_spotify_sdk_redirect_host))
                        .build().toString())
                        .setShowDialog(true)
                        .setScopes(arrayOf("user-read-email"))
                        .setCampaign("your-campaign-token")
                        .build())
    }

    override fun extractData(): SpotifyLoginResult? {
        if (requestCode == com.ticketswap.assessment.SpotifyLogin.CODE && resultCode == Activity.RESULT_OK) {
            val response = AuthenticationClient.getResponse(resultCode, data)
            return SpotifyLoginResult(response.accessToken, response.code, response.error,
                    response.expiresIn, response.state, convertType(response.type))
        }
        return null
    }

    private fun convertType(type: AuthenticationResponse.Type): SpotifyLoginState = when (type) {
        AuthenticationResponse.Type.TOKEN -> SpotifyLoginState.SUCCESS
        else -> SpotifyLoginState.ERROR
    }
}

data class SpotifyLoginResult(val accessToken: String, val code: String, val error: String,
                              val expiresIn: Int, val state: String, val stateType: SpotifyLoginState)

enum class SpotifyLoginState {
    ERROR, SUCCESS
}