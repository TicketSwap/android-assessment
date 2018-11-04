package com.ticketswap.assessment.repository_room

import com.ticketswap.assessment.data.persistance.dao.UserDao
import com.ticketswap.assessment.data.persistance.entity.UserInfo
import com.ticketswap.assessment.repo.SaveTokenRepository
import com.ticketswap.assessment.repo.model.SaveTokenRequest
import io.reactivex.Completable

class SaveTokenRepositoryImpl constructor(private val userDao: UserDao) : SaveTokenRepository() {
    override fun execute(param: SaveTokenRequest): Completable = userDao.saveInfo(
            UserInfo("", param.token, param.expiresIn)
    )
}