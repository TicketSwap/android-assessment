package com.ticketswap.assessment.repository_room

import com.ticketswap.assessment.data.persistance.dao.UserDao
import com.ticketswap.assessment.repo.UserAuthenticatedRepository
import com.ticketswap.assessment.repo.model.UserInfoRequest
import io.reactivex.Scheduler
import io.reactivex.Single

/**
 * @param io: an Scheduler where repository is going to subscribe on
 * @param main: an Scheduler where repository is going to observe on
 */
class UserAuthenticatedRepositoryImpl constructor(
        private val userDao: UserDao, private val io: Scheduler, private val main: Scheduler
) : UserAuthenticatedRepository() {
    override fun execute(params: Unit): Single<UserInfoRequest> =
            userDao.userInfo().map { UserInfoRequest(it.token) }.subscribeOn(io)
                    .observeOn(main)
}