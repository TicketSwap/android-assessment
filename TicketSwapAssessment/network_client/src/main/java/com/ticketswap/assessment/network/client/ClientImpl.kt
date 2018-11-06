package com.ticketswap.assessment.network.client

import com.ticketswap.assessment.datanetwork.SpotifyApi
import com.ticketswap.assessment.datanetwork.model.ArtistInfoResponse
import com.ticketswap.assessment.datanetwork.model.SearchResponse
import com.ticketswap.assessment.datanetwork.model.TrackInfoResponse
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * an implementation for {@link SpotifyApi} with okhttpclient
 */
class ClientImpl(private val networkClient: NetworkClient,
                 private val io: Scheduler,
                 private val main: Scheduler) : SpotifyApi {
    override fun search(url: String, method: String,
                        queryParams: Map<String, String>): Single<SearchResponse> = Single.defer {
        Single.just(networkClient.request(url, method, queryParams, null,
                null, SearchResponse::class.java))
    }.subscribeOn(io).observeOn(main)


    override fun trackInfo(url: String, id: String, method: String): Single<TrackInfoResponse> =
            Single.defer {
                Single.just(networkClient.request(url, method, mapOf(),
                        null, Nothing::class.java, TrackInfoResponse::class.java))
            }.subscribeOn(io).observeOn(main)

    override fun artistInfo(url: String, id: String, method: String): Single<ArtistInfoResponse> =
            Single.defer {
                Single.just(networkClient.request(url, method, mapOf(), null,
                        Nothing::class.java, ArtistInfoResponse::class.java))
            }.subscribeOn(io).observeOn(main)
}