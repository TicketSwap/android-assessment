package com.ticketswap.assessment.repository_room

import com.ticketswap.assessment.data.persistance.dao.UserDao
import com.ticketswap.assessment.model.UserInfoRepo
import com.ticketswap.assessment.repository.UserAuthenticatedRepository

class UserAuthenticatedRepositoryImpl @Inject constructor(
        private val userDao: UserDao
) : UserAuthenticatedRepository() {
    override fun execute(params: Unit): UserInfoRepo = UserInfoRepo(
            userDao.userInfo().token
    )
}