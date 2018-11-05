package com.ticketswap.assessment.data.persistance

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.ticketswap.assessment.data.persistance.dao.ArtistDao
import com.ticketswap.assessment.data.persistance.dao.UserDao
import com.ticketswap.assessment.data.persistance.entity.ArtistEntity
import com.ticketswap.assessment.data.persistance.entity.ExternalUrlEntity
import com.ticketswap.assessment.data.persistance.entity.ImageEntity
import com.ticketswap.assessment.data.persistance.entity.UserInfo

@Database(entities = [
    UserInfo::class, ArtistEntity::class, ImageEntity::class, ExternalUrlEntity::class
], version = 1)
abstract class SpotifyDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    abstract val artistDao: ArtistDao
}