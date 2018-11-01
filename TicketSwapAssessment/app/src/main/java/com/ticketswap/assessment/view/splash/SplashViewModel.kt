package com.ticketswap.assessment.view.splash

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ticketswap.assessment.repository.UserAuthenticatedRepository

class SplashViewModel(private val userAuthenticatedRepository: UserAuthenticatedRepository) : ViewModel() {
    val isUserAuthenticated: MutableLiveData<Boolean> = MutableLiveData()
    fun checkUserIsAuthorized() {
        val info = userAuthenticatedRepository.execute(Unit).value
        if (info?.token.isNullOrEmpty()) {
            isUserAuthenticated.value = false
            return
        }

        isUserAuthenticated.value = true
    }

}