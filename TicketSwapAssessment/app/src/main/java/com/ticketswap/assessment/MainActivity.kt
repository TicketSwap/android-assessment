package com.ticketswap.assessment

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import com.ticketswap.assessment.spotify.SpotifyLoginHelper
import com.ticketswap.assessment.view.login.LoginHelperViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * uses navigation library to navigate main fragment (splash fragment)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
    }

    override fun onSupportNavigateUp(): Boolean =
            findNavController(R.id.nav_host_fragment).navigateUp()

    /**
     * pass data to login fragment via LoginHelperViewModel class.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var helperModel = ViewModelProviders.of(this, viewModelFactory).get(LoginHelperViewModel::class.java)
        val spotifyLogin = SpotifyLoginHelper(requestCode, resultCode, data)
        val response = spotifyLogin.extractData()
        helperModel.loginResponse(response)
    }
}
