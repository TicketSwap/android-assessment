package com.ticketswap.assessment.view.search

import android.arch.lifecycle.MutableLiveData
import com.ticketswap.assessment.BaseViewModel
import com.ticketswap.assessment.domain.model.SearchItem
import com.ticketswap.assessment.domain.model.SearchRequestDomain
import com.ticketswap.assessment.domain.usecase.SearchUseCase
import com.ticketswap.assessment.repo.InsertArtistRepository
import com.ticketswap.assessment.repo.SearchRepository
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase,
                                          private val searchRepository: SearchRepository,
                                          private val insertArtistRepository: InsertArtistRepository) : BaseViewModel() {

    val itemsList = MutableLiveData<List<SearchItem>>()

    private var d: Disposable? = null

    fun searchInputChanged(searchText: String) {
        d?.dispose()
        d = Single.timer(1000, TimeUnit.MILLISECONDS).flatMap {
            searchUseCase.execute(SearchRequestDomain(searchText, "artist"))
        }.map {
            SearchState(result = it.artists.items.map {
                SearchItem(it.name, it.id)
            })
        }.onErrorReturn { SearchState(ex = it, result = listOf()) }.subscribe { t ->
            render(t)
        }

        if (d != null) {
            update(d!!)
        }
    }

    private fun render(item: SearchState) {
        itemsList.value = item.result
        errorLiveData.value = item.ex
    }

}

data class SearchState(val ex: Throwable? = null, val result: List<SearchItem>)

