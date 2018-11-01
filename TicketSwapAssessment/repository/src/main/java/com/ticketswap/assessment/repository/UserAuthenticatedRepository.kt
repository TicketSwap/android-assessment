package com.ticketswap.assessment.repository

import android.arch.lifecycle.LiveData
import com.ticketswap.assessment.model.UserInfoRepo

abstract class UserAuthenticatedRepository : BaseRepository<Unit, LiveData<UserInfoRepo>>