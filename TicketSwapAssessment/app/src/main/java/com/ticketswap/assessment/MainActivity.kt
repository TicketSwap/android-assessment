package com.ticketswap.assessment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainViewModel.checkUserIsAuthorized()

        mainViewModel.isUserAuthenticated.observe(this, Observer<Boolean> {
            if (it == true) {

                return@Observer
            }


        })
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
