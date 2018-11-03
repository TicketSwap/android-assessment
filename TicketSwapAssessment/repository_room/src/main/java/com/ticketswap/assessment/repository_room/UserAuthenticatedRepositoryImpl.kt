package com.ticketswap.assessment.repository_room

import com.ticketswap.assessment.data.persistance.dao.UserDao
import com.ticketswap.assessment.repo.UserAuthenticatedRepository
import com.ticketswap.assessment.repo.model.UserInfoRepo
import io.reactivex.Single

class UserAuthenticatedRepositoryImpl constructor(
        private val userDao: UserDao
) : UserAuthenticatedRepository() {
    override fun execute(params: Unit): Single<UserInfoRepo> = userDao.userInfo().map { UserInfoRepo(it.token) }
}