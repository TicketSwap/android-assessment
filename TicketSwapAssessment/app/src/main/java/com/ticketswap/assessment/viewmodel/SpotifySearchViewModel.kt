package com.ticketswap.assessment.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ticketswap.assessment.RecyclerAdapter
import com.ticketswap.assessment.spotify.Constants
import com.ticketswap.assessment.spotify.SpotifyApi
import io.reactivex.schedulers.Schedulers

class SpotifySearchViewModel(val spotifyApi: SpotifyApi): ViewModel() {

    private val adapter: MutableLiveData<RecyclerAdapter> = MutableLiveData()
    init {
        adapter.value = RecyclerAdapter()
    }

    private val searchText_ : MutableLiveData<String> = MutableLiveData()

    private val error : MutableLiveData<String> = MutableLiveData()

    fun search() {
        spotifyApi
                .searchSpotify(searchText_.value?:"", Constants.API_REQUEST_TYPE_TRACK_AND_ARTIST)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { result ->
                            adapter.value?.items = spotifyApi.convert(result)
                            adapter.postValue(adapter.value)
                        },
                        { error -> setError(error.message) }
                )
    }

    private fun setError(error : String?) {
        this.error.value = error
    }

    fun getError() : MutableLiveData<String> {
        return error
    }

    fun getSearchText() : MutableLiveData<String> {
        return searchText_
    }

    fun getAdapter() : MutableLiveData<RecyclerAdapter> {
        return adapter
    }
}