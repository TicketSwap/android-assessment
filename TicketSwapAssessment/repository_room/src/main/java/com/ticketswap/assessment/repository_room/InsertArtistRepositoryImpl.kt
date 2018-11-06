package com.ticketswap.assessment.repository_room

import com.ticketswap.assessment.data.persistance.dao.ArtistDao
import com.ticketswap.assessment.data.persistance.entity.ArtistEntity
import com.ticketswap.assessment.repo.InsertArtistRepository
import com.ticketswap.assessment.repo.model.ItemDb
import io.reactivex.Completable
import io.reactivex.Scheduler

class InsertArtistRepositoryImpl(private val artistDao: ArtistDao,
                                 private val io: Scheduler,
                                 private val main: Scheduler) : InsertArtistRepository() {

    override fun execute(param: List<ItemDb>): Completable = Completable.fromCallable {
        artistDao.insertArtists(
                param.map {
                    ArtistEntity(id = it.id,
                            image = it.image.sortedBy {
                                it.width
                            }.lastOrNull()?.url,
                            name = it.name, popularity = it.popularity, type = it.type, uri = it.uri)
                }
        )

    }.subscribeOn(io).observeOn(main)
}