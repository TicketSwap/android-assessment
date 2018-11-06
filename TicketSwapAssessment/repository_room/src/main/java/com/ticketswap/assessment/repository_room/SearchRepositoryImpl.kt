package com.ticketswap.assessment.repository_room

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.ticketswap.assessment.data.persistance.dao.ArtistDao
import com.ticketswap.assessment.repo.SearchRepository
import com.ticketswap.assessment.repo.model.ImageDb
import com.ticketswap.assessment.repo.model.ItemDb
import com.ticketswap.assessment.repo.model.SearchRequest

class SearchRepositoryImpl(private val artistDao: ArtistDao) : SearchRepository() {
    override fun execute(param: SearchRequest): LiveData<List<ItemDb>> =
            Transformations.map(artistDao.searchArtist("%${param.query}%")
            ) {
                it.map {
                    ItemDb(it.id, listOf(
                            ImageDb(0, it.image, 0)
                    ),
                            it.name, it.popularity, it.type, it.uri)
                }
            }
}