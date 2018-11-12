package com.ticketswap.assessment.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ticketswap.assessment.R
import com.ticketswap.assessment.SingleLayoutAdapter
import com.ticketswap.assessment.entity.ArtistEntity
import com.ticketswap.assessment.entity.TrackEntity
import com.ticketswap.assessment.spotify.Constants
import com.ticketswap.assessment.spotify.SearchResponse
import com.ticketswap.assessment.spotify.SpotifyApi
import io.reactivex.schedulers.Schedulers

class SpotifySearchViewModel(val spotifyApi: SpotifyApi, val selectionListener: SelectionListener): ViewModel() {

    val adapter: MutableLiveData<SingleLayoutAdapter> = MutableLiveData()
    val searchText : MutableLiveData<String> = MutableLiveData()
    val error : MutableLiveData<String> = MutableLiveData()

    var lastSearchResponse : SearchResponse? = null

    init {
        adapter.value = SingleLayoutAdapter(this, R.layout.item) //RecyclerAdapter()
    }

    fun search() {
        spotifyApi
                .searchSpotify(searchText.value?:"", Constants.API_REQUEST_TYPE_TRACK_AND_ARTIST)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { result ->
                            lastSearchResponse = result
                            adapter.value?.items = spotifyApi.convert(result)
                            adapter.postValue(adapter.value)
                        },
                        { error -> setError(error.message) }
                )
    }

    private fun setError(error : String?) {
        this.error.value = error
    }

    fun selectTitle(title: String) {
        val name = title.substring(title.indexOf(":") +2)
        var artist: ArtistEntity? = null
        var track: TrackEntity? = null
        if (title.startsWith("Artist")) {
            artist = lastSearchResponse?.artists?.items?.filter { it.name == name }?.firstOrNull()
        } else {
            track = lastSearchResponse?.tracks?.items?.filter { it.name == name }?.firstOrNull()
        }
        selectionListener.select(artist, track)
    }

    class SpotifySearchViewModelFactory(val spotifyApi: SpotifyApi, val selectionListener: SpotifySearchViewModel.SelectionListener) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SpotifySearchViewModel(spotifyApi, selectionListener) as T
        }
    }

    interface SelectionListener {
        fun select(artist: ArtistEntity?, track: TrackEntity?)
    }
}