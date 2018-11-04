package com.ticketswap.assessment.data.persistance.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.ticketswap.assessment.data.persistance.entity.UserInfo
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM userinfo")
    fun userInfo(): Single<UserInfo>

    @Insert
    fun saveInfo(userInfo: UserInfo): Completable
}