package com.ticketswap.assessment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.ticketswap.assessment.spotify.SpotifyApi
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

import com.ticketswap.assessment.databinding.ActivityMainBinding
import com.ticketswap.assessment.viewmodel.SpotifySearchViewModel
import com.ticketswap.assessment.viewmodel.SpotifySearchViewModelFactory

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var spotifyApi: SpotifyApi

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = SpotifySearchViewModelFactory(spotifyApi);
        val viewModel = ViewModelProviders.of(this, factory).get(SpotifySearchViewModel::class.java)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setViewModel(viewModel)
    }

}
