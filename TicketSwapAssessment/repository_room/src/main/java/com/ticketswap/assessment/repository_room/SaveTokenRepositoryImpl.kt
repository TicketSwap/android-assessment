package com.ticketswap.assessment.repository_room

import com.ticketswap.assessment.data.persistance.dao.UserDao
import com.ticketswap.assessment.data.persistance.entity.UserInfo
import com.ticketswap.assessment.repo.SaveTokenRepository
import com.ticketswap.assessment.repo.model.SaveTokenRequest
import io.reactivex.Completable
import io.reactivex.Scheduler

class SaveTokenRepositoryImpl constructor(private val userDao: UserDao,
                                          private val io: Scheduler,
                                          private val main: Scheduler) : SaveTokenRepository() {
    override fun execute(param: SaveTokenRequest): Completable = Completable.fromCallable {
        userDao.saveInfo(
                UserInfo(0, param.token, param.expiresIn)
        )
    }.subscribeOn(io).observeOn(main)
}