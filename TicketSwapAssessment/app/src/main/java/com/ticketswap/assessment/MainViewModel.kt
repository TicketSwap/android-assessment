package com.ticketswap.assessment

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ticketswap.assessment.data.persistance.dao.UserDao

class MainViewModel(private val userDao: UserDao) : ViewModel() {
    val isUserAuthenticated: MutableLiveData<Boolean> = MutableLiveData()
    fun checkUserIsAuthorized() {
        val info = userDao.userInfo()
        isUserAuthenticated.value = info.token?.isEmpty() == false
    }

}