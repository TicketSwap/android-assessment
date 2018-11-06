package com.ticketswap.assessment.repository_room

import com.ticketswap.assessment.data.persistance.dao.ArtistDao
import com.ticketswap.assessment.repo.SearchRepository
import com.ticketswap.assessment.repo.model.*
import io.reactivex.Single

class SearchRepositoryImpl(private val artistDao: ArtistDao) : SearchRepository() {
    override fun execute(param: SearchRequest): Single<ArtistsDb> =
            artistDao.search("%${param.query}%").map {
                ArtistsDb(it.map {
                    ItemDb(
                            ExternalUrlDb(it.externalUrls?.spotify ?: ""),
                            it.genres,
                            it.href,
                            it.id.toString(),
                            it.images.map { ImageDb(it.height, it.url, it.width) },
                            it.name,
                            it.popularity,
                            it.type,
                            it.uri
                    )
                })
            }
}