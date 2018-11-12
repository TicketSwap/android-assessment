package com.ticketswap.assessment.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ticketswap.assessment.entity.TrackEntity

class TrackDetailViewModel(val track: TrackEntity?): ViewModel() {


    class TrackViewModelFactory(val track: TrackEntity?) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TrackDetailViewModel(track) as T
        }
    }

    fun getTitle() : String? {
        return track?.name
    }

}