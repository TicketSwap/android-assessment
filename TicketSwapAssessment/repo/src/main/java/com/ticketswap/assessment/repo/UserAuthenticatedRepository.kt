package com.ticketswap.assessment.repo

import com.ticketswap.assessment.repo.model.UserInfoRepo
import io.reactivex.Maybe

abstract class UserAuthenticatedRepository : BaseRepository<Unit, Maybe<UserInfoRepo>>()