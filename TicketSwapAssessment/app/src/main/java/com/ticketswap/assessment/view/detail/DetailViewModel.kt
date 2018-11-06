package com.ticketswap.assessment.view.detail

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.ticketswap.assessment.BaseViewModel
import com.ticketswap.assessment.domain.model.ArtistItem
import com.ticketswap.assessment.domain.usecase.ArtistUseCase
import com.ticketswap.assessment.repo.ArtistRepository
import com.ticketswap.assessment.repo.InsertArtistRepository
import com.ticketswap.assessment.repo.model.ImageDb
import com.ticketswap.assessment.repo.model.ItemDb
import javax.inject.Inject

class DetailViewModel @Inject constructor(
        private val artistRepository: ArtistRepository,
        private val artistUseCase: ArtistUseCase,
        private val insertArtistRepository: InsertArtistRepository
) : BaseViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    // live data for update UI
    fun loadArtistInfoLocal(id: String) = Transformations.map(
            artistRepository.execute(id)
    ) {
        ArtistItem(it.id, it.image.firstOrNull()?.url, it.name, it.popularity, it.type, it.uri)
    }

    /**
     * @param artistId : artist id for query the API
     * fetch API data
     */
    @SuppressLint("CheckResult")
    fun loadArtistInfo(artistId: String) {
        render(DetailState(isLoading = true))
        update(artistUseCase.execute(artistId)
                .map {
                    DetailState(artistItem = ArtistItem(it.id, it.image, it.name,
                            it.popularity, it.type, it.uri), isLoading = false)
                }
                .onErrorReturn { DetailState(ex = it, isLoading = false) }
                .subscribe { it ->
                    render(it)
                }
        )
    }

    private fun render(item: DetailState) {
        isLoading.value = item.isLoading
        if (item.ex != null) {
            errorLiveData.value = item.ex
            return
        }

        item.artistItem?.run {
            insertArtistRepository.execute(listOf(
                    ItemDb(id, listOf(ImageDb(0, image, 0)), name, popularity,
                            type, uri)
            ))
        }

    }


}

data class DetailState(val ex: Throwable? = null, val artistItem: ArtistItem? = null, val isLoading: Boolean = false)