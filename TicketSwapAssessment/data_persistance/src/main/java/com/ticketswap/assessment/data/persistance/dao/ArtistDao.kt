package com.ticketswap.assessment.data.persistance.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.ticketswap.assessment.data.persistance.entity.ArtistEntity

@Dao
abstract class ArtistDao {
    @Query("select * from artistentity where name like :query")
    abstract fun searchArtist(query: String): LiveData<List<ArtistEntity>>

    @Query("select * from artistentity where id=:id")
    abstract fun loadArtist(id: String): LiveData<ArtistEntity>

    @Transaction
    open fun insertArtists(artists: List<ArtistEntity>) {
        artists.forEach {
            insertArtist(it)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertArtist(artistEntity: ArtistEntity)
}