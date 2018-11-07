package com.ticketswap.assessment.view.search

import android.arch.lifecycle.Observer
import com.ticketswap.assessment.domain.model.ArtistsDomain
import com.ticketswap.assessment.domain.model.ItemDomain
import com.ticketswap.assessment.domain.model.SearchRequestDomain
import com.ticketswap.assessment.domain.model.SearchResponseDomain
import com.ticketswap.assessment.domain.usecase.SearchUseCase
import com.ticketswap.assessment.repo.InsertArtistRepository
import com.ticketswap.assessment.repo.SearchRepository
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

class SearchViewModelTest {

    private lateinit var searchViewModel: SearchViewModel

    @Mock
    private lateinit var searchUseCase: SearchUseCase

    @Mock
    private lateinit var searchRepository: SearchRepository

    @Mock
    private lateinit var insertArtistRepository: InsertArtistRepository

    @Mock
    private lateinit var errorObserver: Observer<Throwable?>

    private lateinit var io: TestScheduler

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        io = TestScheduler()
        val main = TestScheduler()
        searchViewModel = SearchViewModel(searchUseCase, searchRepository, insertArtistRepository, io, main)
    }


    @Test
    fun `check response when given input query`() {
        `when`(searchUseCase.execute(SearchRequestDomain("sila", "artist")))
                .thenReturn(Single.just(SearchResponseDomain(ArtistsDomain(
                        "", listOf(
                        ItemDomain(
                                null, listOf(), "", "someId", listOf(),
                                "sila", 80, "type", "uri"
                        )
                ), 10, "", 0, null, 100
                ))))

        searchViewModel.getArtistObservable("sila").test()
                .awaitCount(1) { io.advanceTimeBy(1200, TimeUnit.MILLISECONDS) }
                .assertResult(SearchState(ex = null, result = listOf(
                        ItemDomain(null, listOf(), "", "someId", listOf(),
                                "sila", 80, "type", "uri")
                )))


    }

}