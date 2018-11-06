package com.ticketswap.assessment.view.search

import android.arch.lifecycle.MutableLiveData
import com.ticketswap.assessment.BaseViewModel
import com.ticketswap.assessment.domain.model.SearchItem
import com.ticketswap.assessment.domain.model.SearchRequestDomain
import com.ticketswap.assessment.domain.usecase.SearchUseCase
import com.ticketswap.assessment.repo.InsertArtistRepository
import com.ticketswap.assessment.repo.SearchRepository
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Named

class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase,
                                          private val searchRepository: SearchRepository,
                                          private val insertArtistRepository: InsertArtistRepository,
                                          @Named("io") private val io: Scheduler,
                                          @Named("main") private val main: Scheduler) : BaseViewModel() {

    val itemsList = MutableLiveData<List<SearchAdapterItem>>()

    private var d: Disposable? = null

    fun searchInputChanged(searchText: String) {
        d?.dispose()
        d = Maybe.just(searchText).filter { it.isNotEmpty() && it.length > 3 }.toSingle().flatMap {
            Single.timer(1000, TimeUnit.MILLISECONDS)
        }.flatMap {
            searchUseCase.execute(SearchRequestDomain(searchText, "artist"))
        }.map {
            SearchState(result = it.artists.items.map {
                SearchItem(it.name, it.id, it.images.firstOrNull()?.url)
            })
        }.subscribeOn(io).observeOn(main).doOnSubscribe {
            update(it)
        }.onErrorReturn { SearchState(ex = it, result = listOf()) }.subscribe { t ->
            render(t)
        }

    }

    private fun render(item: SearchState) {
        item.result.map {
            SearchAdapterItem(it.id, it.image, it.name, SearchItemType.LOCAL)
        }.apply {
            itemsList.value = this
        }
        errorLiveData.value = item.ex
    }

}

data class SearchState(val ex: Throwable? = null, val result: List<SearchItem>)

