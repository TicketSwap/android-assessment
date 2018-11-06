package com.ticketswap.assessment.repo

import android.arch.lifecycle.LiveData
import com.ticketswap.assessment.repo.model.ItemDb

abstract class ArtistRepository : BaseRepository<String, LiveData<ItemDb>>()