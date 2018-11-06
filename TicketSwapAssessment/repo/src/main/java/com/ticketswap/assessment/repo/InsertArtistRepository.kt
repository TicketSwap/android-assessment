package com.ticketswap.assessment.repo

import com.ticketswap.assessment.repo.model.ArtistsDb

abstract class InsertArtistRepository : BaseRepository<ArtistsDb, List<Long>>()