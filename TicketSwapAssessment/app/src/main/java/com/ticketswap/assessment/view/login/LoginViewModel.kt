package com.ticketswap.assessment.view.login

import android.arch.lifecycle.MutableLiveData
import com.ticketswap.assessment.BaseViewModel
import com.ticketswap.assessment.spotify.SpotifyLoginResult
import com.ticketswap.assessment.spotify.SpotifyLoginState
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    val isLoginClicked = MutableLiveData<Unit>()
    val navigateToSearch = MutableLiveData<Unit>()

    fun loginClicked() {
        isLoginClicked.value = Unit
    }

    fun checkLoginStatus(response: SpotifyLoginResult?) {
        response?.also {
            if (it.stateType == SpotifyLoginState.SUCCESS) {
                navigateToSearch.value = Unit
                return
            }


        }
    }

}