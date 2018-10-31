package com.ticketswap.assessment.network.client

import com.ticketswap.assessment.datanetwork.SpotifyApi
import com.ticketswap.assessment.datanetwork.model.ArtistInfoResponse
import com.ticketswap.assessment.datanetwork.model.SearchResponse
import com.ticketswap.assessment.datanetwork.model.TrackInfoResponse
import io.reactivex.Single

class ClientImpl(private val networkClient: NetworkClient) : SpotifyApi {
    override fun search(url: String, method: String, queryParams: Map<String, String>): Single<SearchResponse> =
            Single.just(networkClient.request(url, method, queryParams, null,
                    Nothing::class.java, SearchResponse::class.java))

    override fun trackInfo(url: String, id: String, method: String): Single<TrackInfoResponse> =
            Single.just(networkClient.request(url, method, mapOf(),
                    null, Nothing::class.java, TrackInfoResponse::class.java))

    override fun artistInfo(url: String, id: String, method: String): Single<ArtistInfoResponse> =
            Single.just(networkClient.request(url, method, mapOf(), null,
                    Nothing::class.java, ArtistInfoResponse::class.java))
}