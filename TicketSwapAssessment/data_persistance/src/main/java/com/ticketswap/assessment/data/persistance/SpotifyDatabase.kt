package com.ticketswap.assessment.data.persistance

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ticketswap.assessment.data.persistance.dao.UserDao
import com.ticketswap.assessment.data.persistance.entity.UserInfo

@Database(entities = [UserInfo::class], version = 1)
abstract class SpotifyDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}