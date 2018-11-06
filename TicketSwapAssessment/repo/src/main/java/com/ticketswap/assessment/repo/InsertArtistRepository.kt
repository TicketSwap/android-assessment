package com.ticketswap.assessment.repo

import com.ticketswap.assessment.repo.model.ItemDb
import io.reactivex.Completable

abstract class InsertArtistRepository : BaseRepository<List<ItemDb>, Completable>()