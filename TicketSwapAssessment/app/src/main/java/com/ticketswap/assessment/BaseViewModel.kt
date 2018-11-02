package com.ticketswap.assessment

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    private val errorLiveData: MutableLiveData<String> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}