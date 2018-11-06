package com.ticketswap.assessment.datanetwork

import com.ticketswap.assessment.datanetwork.model.ArtistInfoResponse
import com.ticketswap.assessment.datanetwork.model.SearchResponse
import com.ticketswap.assessment.datanetwork.model.TrackInfoResponse
import io.reactivex.Single

/**
 * interface for api calls
 */
interface SpotifyApi {
    fun search(url: String = "search", method: String = "GET", queryParams: Map<String, String>): Single<SearchResponse>
    fun trackInfo(url: String = "tracks/{id}", id: String, method: String = "GET"): Single<TrackInfoResponse>
    fun artistInfo(url: String = "artists/{id}", id: String, method: String = "GET"): Single<ArtistInfoResponse>
}