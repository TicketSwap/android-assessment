package com.ticketswap.assessment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.ticketswap.assessment.spotify.SpotifyApi
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

import com.ticketswap.assessment.databinding.ActivityMainBinding
import com.ticketswap.assessment.entity.ArtistEntity
import com.ticketswap.assessment.entity.TrackEntity
import com.ticketswap.assessment.viewmodel.SpotifySearchViewModel
import com.ticketswap.assessment.AbstractDetailActivity.IntentFactory

class MainActivity : DaggerAppCompatActivity(), SpotifySearchViewModel.SelectionListener {

    @Inject
    lateinit var spotifyApi: SpotifyApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = SpotifySearchViewModel.SpotifySearchViewModelFactory(spotifyApi, this);
        val viewModel = ViewModelProviders.of(this, factory).get(SpotifySearchViewModel::class.java)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setViewModel(viewModel)
    }

    override fun select(artist: ArtistEntity?, track: TrackEntity?) {
        artist?.let {
            val intent = IntentFactory<ArtistEntity>(ArtistEntity::class.java).createIntent(this, artist)
            startActivity(intent)
        }
        track?.let {
            val intent = IntentFactory<TrackEntity>(TrackEntity::class.java).createIntent(this, track)
            startActivity(intent)
        }
    }
}
