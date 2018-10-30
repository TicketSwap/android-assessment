package com.ticketswap.assessment

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

//    fun startActivityResult() {
//        val spotifyClientId = "84ea753e599142b8bace9b63d153227b" // Feel free to use this spotify app
//
//        AuthenticationClient.openLoginActivity(this, 137,
//                AuthenticationRequest.Builder(
//                        spotifyClientId,
//                        AuthenticationResponse.Type.TOKEN, Uri.Builder()
//                        .scheme(getString(R.string.com_spotify_sdk_redirect_scheme))
//                        .authority(getString(R.string.com_spotify_sdk_redirect_host))
//                        .build().toString())
//                        .setShowDialog(true)
//                        .setScopes(arrayOf("user-read-email"))
//                        .setCampaign("your-campaign-token")
//                        .build()
//        )
//    }

}
