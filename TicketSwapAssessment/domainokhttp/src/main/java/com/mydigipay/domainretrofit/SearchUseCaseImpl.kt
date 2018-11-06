package com.mydigipay.domainretrofit

import com.ticketswap.assessment.datanetwork.SpotifyApi
import com.ticketswap.assessment.domain.model.*
import com.ticketswap.assessment.domain.usecase.SearchUseCase
import io.reactivex.Single

class SearchUseCaseImpl(private val spotifyApi: SpotifyApi) : SearchUseCase() {

    override fun execute(param: SearchRequestDomain): Single<SearchResponseDomain> =
            spotifyApi.search(queryParams = mutableMapOf(
                    Pair("type", param.type)
            ).apply {
                if (param.query.isNullOrEmpty().not()) put("q", param.query!!)
            }.toMap()).map { response ->
                SearchResponseDomain(
                        ArtistsDomain(
                                response.artists.href,
                                response.artists.items.map { item ->
                                    ItemDomain(
                                            item.externalUrls?.let { ExternalUrlsDomain(it.spotify) },
                                            item.genres,
                                            item.href,
                                            item.id,
                                            item.images.map { ImageDomain(it.height, it.url, it.width) },
                                            item.name,
                                            item.popularity,
                                            item.type,
                                            item.uri
                                    )
                                },
                                response.artists.limit,
                                response.artists.next,
                                response.artists.offset,
                                response.artists.previous,
                                response.artists.total
                        )
                )
            }
}