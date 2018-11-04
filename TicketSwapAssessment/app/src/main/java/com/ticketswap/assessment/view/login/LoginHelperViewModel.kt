package com.ticketswap.assessment.view.login

import android.arch.lifecycle.MutableLiveData
import com.ticketswap.assessment.BaseViewModel
import com.ticketswap.assessment.repo.SaveTokenRepository
import com.ticketswap.assessment.repo.model.SaveTokenRequest
import com.ticketswap.assessment.spotify.SpotifyLoginResult
import com.ticketswap.assessment.spotify.SpotifyLoginState
import javax.inject.Inject

class LoginHelperViewModel @Inject constructor(private val saveTokenRepository: SaveTokenRepository) : BaseViewModel() {
    val navigateToSearch = MutableLiveData<Unit>()
    fun loginResponse(response: SpotifyLoginResult?) {
        response?.also {
            if (it.stateType == SpotifyLoginState.SUCCESS) {
                update(saveTokenRepository.execute(SaveTokenRequest(it.accessToken, it.expiresIn)).subscribe {
                    navigateToSearch.value = Unit
                })
            }
        }
    }

}