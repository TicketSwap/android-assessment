package com.ticketswap.assessment.repo

import com.ticketswap.assessment.repo.model.ArtistsDb
import io.reactivex.Completable

abstract class InsertArtistRepository : BaseRepository<ArtistsDb, Completable>()