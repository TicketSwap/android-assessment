package com.ticketswap.assessment.view.login

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.ticketswap.assessment.BaseViewModel
import com.ticketswap.assessment.repo.ClearUserInfoRepository
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val clearUserInfoRepository: ClearUserInfoRepository) : BaseViewModel() {

    val isLoginClicked = MutableLiveData<Unit>()
    val navigateToSearch = MutableLiveData<Unit>()

    fun loginClicked() {
        isLoginClicked.value = Unit
    }

    fun clearDatabase(clearUserInfo: Boolean) {
        if (clearUserInfo) {
            clearUserInfoRepository.execute(Unit).subscribe {
                render()
            }
        }

    }

    private fun render() {
        Log.d("test", "Tes")
    }

}

data class LoginState(val ex: Throwable? = null)