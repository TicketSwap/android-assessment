package com.ticketswap.assessment.datanetwork

import com.ticketswap.assessment.datanetwork.model.ArtistInfoResponse
import com.ticketswap.assessment.datanetwork.model.SearchResponse
import com.ticketswap.assessment.datanetwork.model.TrackInfoResponse
import io.reactivex.Single

/**
 * interface for api calls
 */
interface SpotifyApi {
    fun search(url: String = "v1/search", method: String = "GET", queryParams: Map<String, String>): Single<SearchResponse>
    fun trackInfo(url: String = "v1/tracks/{id}", id: String, method: String = "GET"): Single<TrackInfoResponse>
    fun artistInfo(url: String = "v1/artists/{id}", id: String, method: String = "GET"): Single<ArtistInfoResponse>
}