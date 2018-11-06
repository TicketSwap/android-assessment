package com.ticketswap.assessment.data.persistance.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.ticketswap.assessment.data.persistance.entity.ArtistEntity
import io.reactivex.Single

@Dao
interface ArtistDao {
    @Query("select * from artistentity where name like :query")
    fun search(query: String): Single<List<ArtistEntity>>

    @Insert
    fun insertArtist(artistEntity: List<ArtistEntity>): List<Long>
}