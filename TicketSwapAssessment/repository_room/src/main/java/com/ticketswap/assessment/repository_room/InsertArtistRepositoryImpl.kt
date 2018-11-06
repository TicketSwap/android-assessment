package com.ticketswap.assessment.repository_room

import com.ticketswap.assessment.data.persistance.dao.ArtistDao
import com.ticketswap.assessment.data.persistance.entity.ArtistEntity
import com.ticketswap.assessment.data.persistance.entity.ExternalUrlEntity
import com.ticketswap.assessment.data.persistance.entity.ImageEntity
import com.ticketswap.assessment.repo.InsertArtistRepository
import com.ticketswap.assessment.repo.model.ArtistsDb

class InsertArtistRepositoryImpl(private val artistDao: ArtistDao) : InsertArtistRepository() {

    override fun execute(param: ArtistsDb): List<Long> = artistDao.insertArtist(param.items.map {
        ArtistEntity(it.id, ExternalUrlEntity(0, it.id, it.externalUrls.spotify),
                it.genres, it.href, it.images.map { img -> ImageEntity(0, it.id, img.height, img.url, img.width) },
                it.name, it.popularity, it.type, it.uri)
    })
}