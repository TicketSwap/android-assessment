package com.ticketswap.assessment.repo

import io.reactivex.Completable

abstract class ClearUserInfoRepository : BaseRepository<Unit, Completable>()