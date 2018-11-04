package com.ticketswap.assessment.data.persistance.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.ticketswap.assessment.data.persistance.entity.UserInfo
import io.reactivex.Maybe

@Dao
interface UserDao {
    @Query("SELECT * FROM userinfo")
    fun userInfo(): Maybe<UserInfo>
}