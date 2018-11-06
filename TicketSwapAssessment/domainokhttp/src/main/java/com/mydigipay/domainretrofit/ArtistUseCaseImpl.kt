package com.mydigipay.domainretrofit

import com.ticketswap.assessment.datanetwork.SpotifyApi
import com.ticketswap.assessment.domain.model.ArtistDomain
import com.ticketswap.assessment.domain.usecase.ArtistUseCase
import io.reactivex.Scheduler
import io.reactivex.Single

class ArtistUseCaseImpl(private val spotifyApi: SpotifyApi,
                        private val io: Scheduler,
                        private val main: Scheduler) : ArtistUseCase() {
    override fun execute(param: String): Single<ArtistDomain> = spotifyApi.artistInfo(id = param)
            .map {
                ArtistDomain(it.href, it.images.sortedBy { it.width }.lastOrNull()?.url,
                        it.popularity, it.id, it.name, it.type, it.uri)
            }.subscribeOn(io).observeOn(main)
}