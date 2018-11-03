package com.ticketswap.assessment.repo

import com.ticketswap.assessment.repo.model.UserInfoRepo
import io.reactivex.Single

abstract class UserAuthenticatedRepository : BaseRepository<Unit, Single<UserInfoRepo>>()