package com.ticketswap.assessment.data.persistance.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class UserInfo(
        @PrimaryKey val id: String,
        @ColumnInfo(name = "token") val token: String?
)