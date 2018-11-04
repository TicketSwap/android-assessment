package com.ticketswap.assessment.view.splash

import android.arch.lifecycle.MutableLiveData
import com.ticketswap.assessment.BaseViewModel
import com.ticketswap.assessment.repo.UserAuthenticatedRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(val userAuthenticatedRepository: UserAuthenticatedRepository) : BaseViewModel() {
    val navigateToLogin: MutableLiveData<Unit> = MutableLiveData()
    val navigateToSearch: MutableLiveData<Unit> = MutableLiveData()
    fun checkUserIsAuthorized() {
        update(userAuthenticatedRepository.execute(Unit).map { SplashState(token = it.token) }
                .onErrorReturn { SplashState(err = it) }.subscribe { state ->
                    render(state)
                })

    }

    private fun render(state: SplashState) {
        if (state.err != null) {
            errorLiveData.value = state.err
            navigateToLogin.value = Unit
            return
        }

        if (state.token.isNullOrEmpty()) {
            navigateToLogin.value = Unit
            return
        }

        navigateToSearch.value = Unit
    }

}

data class SplashState(val token: String? = null, val err: Throwable? = null)
