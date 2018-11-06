package com.ticketswap.assessment.repository_room

import com.ticketswap.assessment.data.persistance.dao.UserDao
import com.ticketswap.assessment.repo.ClearUserInfoRepository
import io.reactivex.Completable
import io.reactivex.Scheduler

class ClearUserInfoRepositoryImpl constructor(private val userDao: UserDao,
                                              private val io: Scheduler,
                                              private val main: Scheduler) : ClearUserInfoRepository() {
    override fun execute(param: Unit): Completable = Completable.fromCallable {
        try {
            val res = userDao.userInfo().blockingGet()
            userDao.clearInfo(res)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }.subscribeOn(io).observeOn(main)
}