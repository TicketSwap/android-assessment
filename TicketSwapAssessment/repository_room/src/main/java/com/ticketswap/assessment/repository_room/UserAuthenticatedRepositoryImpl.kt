package com.ticketswap.assessment.repository_room

import com.ticketswap.assessment.data.persistance.dao.UserDao
import com.ticketswap.assessment.repo.UserAuthenticatedRepository
import com.ticketswap.assessment.repo.model.UserInfoRepo
import io.reactivex.Maybe
import io.reactivex.Scheduler

/**
 * @param io: an Scheduler where repository is going to subscribe on
 * @param main: an Scheduler where repository is going to observe on
 */
class UserAuthenticatedRepositoryImpl constructor(
        private val userDao: UserDao, private val io: Scheduler, private val main: Scheduler
) : UserAuthenticatedRepository() {
    override fun execute(params: Unit): Maybe<UserInfoRepo> =
            userDao.userInfo().map { UserInfoRepo(it.token) }.subscribeOn(io)
                    .observeOn(main)
}