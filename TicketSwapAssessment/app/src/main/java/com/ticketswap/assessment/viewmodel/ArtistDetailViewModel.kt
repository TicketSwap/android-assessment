package com.ticketswap.assessment.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ticketswap.assessment.entity.ArtistEntity


class ArtistDetailViewModel(val artist: ArtistEntity?): ViewModel() {

    class ArtistViewModelFactory(val artist: ArtistEntity?) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArtistDetailViewModel(artist) as T
        }
    }

    fun getTitle() : String? {
        return artist?.name
    }

    fun getImage() : String? {
        return artist?.images?.get(0)?.url
    }
}