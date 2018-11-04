package com.ticketswap.assessment.view.login

import android.arch.lifecycle.MutableLiveData
import com.ticketswap.assessment.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : BaseViewModel() {

    val isLoginClicked = MutableLiveData<Unit>()
    val navigateToSearch = MutableLiveData<Unit>()

    fun loginClicked() {
        isLoginClicked.value = Unit
    }

}