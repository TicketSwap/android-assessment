package com.ticketswap.assessment.view.search

import android.arch.lifecycle.Transformations
import android.util.Log
import com.ticketswap.assessment.BaseViewModel
import com.ticketswap.assessment.domain.model.ItemDomain
import com.ticketswap.assessment.domain.model.SearchRequestDomain
import com.ticketswap.assessment.domain.usecase.SearchUseCase
import com.ticketswap.assessment.repo.InsertArtistRepository
import com.ticketswap.assessment.repo.SearchRepository
import com.ticketswap.assessment.repo.model.ImageDb
import com.ticketswap.assessment.repo.model.ItemDb
import com.ticketswap.assessment.repo.model.SearchRequest
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

    // we dispose with any text change to prevent many requests to server side
    private var queryChangeDisposable: Disposable? = null

    /**
     * @param searchText : text that we want to observe for data changes.
     * @return LiveData<List<SearchAdapterItem>> UI will listen to database changes and then will
     * update itself.
     */
    fun changeObserver(searchText: String) =
            Transformations.map(searchRepository.execute(SearchRequest(searchText, "artist"))) {
                it.map { SearchAdapterItem(it.id, it.image.firstOrNull()?.url, it.name, SearchItemType.LOCAL) }
            }


    /**
     * @param searchText : text that we want to fetch data from API.
     * we will fetch data from spotify api. before request anything, we will wait for a second
     * to ensure that user does not enter any character.
     */
    fun getArtistsFromCloud(searchText: String) {
        queryChangeDisposable?.dispose()
        queryChangeDisposable = getArtistObservable(searchText)
                .subscribeOn(io).observeOn(main).doOnSubscribe {
            update(it)
        }.onErrorReturn { SearchState(ex = it, result = listOf()) }.subscribe { t ->
            render(t)
        }
    }

    fun getArtistObservable(searchText: String): Single<SearchState> =
            Maybe.just(searchText).filter { it.isNotEmpty() && it.length > 3 }.toSingle().flatMap {
                Single.timer(1000, TimeUnit.MILLISECONDS, io)
            }.flatMap {
                searchUseCase.execute(SearchRequestDomain(searchText, "artist"))
            }.map {
                SearchState(result = it.artists.items)
            }


    /*
     * handle the error if we have any error, aside that we will insert data to database
     */
    fun render(item: SearchState) {
        if (item.ex != null) {
            errorLiveData.postValue(item.ex)
            return
        }

        update(insertArtistRepository.execute(item.result.map {
            ItemDb(it.id, it.images.map { ImageDb(it.height, it.url, it.width) },
                    it.name, it.popularity, it.type, it.uri)
        }).subscribe {
            Log.d("db", "insert artists to database")
        })
    }

}

/**
 * state for this viewModel
 */
data class SearchState(val ex: Throwable? = null, val result: List<ItemDomain>)

