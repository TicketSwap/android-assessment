package com.ticketswap.assessment.repo

import com.ticketswap.assessment.repo.model.ArtistsDb
import com.ticketswap.assessment.repo.model.SearchRequest
import io.reactivex.Single

abstract class SearchRepository : BaseRepository<SearchRequest, Single<ArtistsDb>>()