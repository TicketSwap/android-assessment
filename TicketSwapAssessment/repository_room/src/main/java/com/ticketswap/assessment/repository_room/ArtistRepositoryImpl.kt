package com.ticketswap.assessment.repository_room

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.ticketswap.assessment.data.persistance.dao.ArtistDao
import com.ticketswap.assessment.repo.ArtistRepository
import com.ticketswap.assessment.repo.model.ImageDb
import com.ticketswap.assessment.repo.model.ItemDb

class ArtistRepositoryImpl(private val artistDao: ArtistDao) : ArtistRepository() {
    override fun execute(param: String): LiveData<ItemDb> =
            Transformations.map(artistDao.loadArtist(param)) {
                ItemDb(it.id, listOf(
                        ImageDb(0, it.image, 0)
                ), it.name, it.popularity, it.type, it.uri)
            }
}