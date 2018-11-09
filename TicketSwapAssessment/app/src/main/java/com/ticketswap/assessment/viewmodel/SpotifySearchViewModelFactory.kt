package com.ticketswap.assessment.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ticketswap.assessment.spotify.SpotifyApi

class SpotifySearchViewModelFactory(val spotifyApi: SpotifyApi) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SpotifySearchViewModel(spotifyApi) as T
    }
}