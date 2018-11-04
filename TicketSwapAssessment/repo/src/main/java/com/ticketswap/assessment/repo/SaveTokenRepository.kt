package com.ticketswap.assessment.repo

import com.ticketswap.assessment.repo.model.SaveTokenRequest
import io.reactivex.Completable

abstract class SaveTokenRepository : BaseRepository<SaveTokenRequest, Completable>()