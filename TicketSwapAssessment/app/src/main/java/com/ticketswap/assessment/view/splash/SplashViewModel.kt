package com.ticketswap.assessment.view.splash

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ticketswap.assessment.repository.UserAuthenticatedRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(val userAuthenticatedRepository: UserAuthenticatedRepository) : ViewModel() {
    val isUserAuthenticated: MutableLiveData<Boolean> = MutableLiveData()
    fun checkUserIsAuthorized() {
        val info = userAuthenticatedRepository.execute(Unit)
        if (info?.token.isNullOrEmpty()) {
            isUserAuthenticated.value = false
            return
        }

        isUserAuthenticated.value = true
    }

}