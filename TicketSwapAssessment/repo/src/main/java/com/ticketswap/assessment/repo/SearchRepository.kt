package com.ticketswap.assessment.repo

import android.arch.lifecycle.LiveData
import com.ticketswap.assessment.repo.model.ItemDb
import com.ticketswap.assessment.repo.model.SearchRequest

abstract class SearchRepository : BaseRepository<SearchRequest, LiveData<List<ItemDb>>>()