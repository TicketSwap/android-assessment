package com.ticketswap.assessment

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    protected val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun update(disposable: Disposable) {
        disposables.add(disposable)
    }
}