package com.ticketswap.assessment

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    /**
     * keeps track of all the disposables
     */
    private val disposables: CompositeDisposable = CompositeDisposable()
    protected val errorLiveData: MutableLiveData<Throwable> = MutableLiveData()

    /**
     * clear disposables and thus stop any api call and etc.
     */
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun update(disposable: Disposable) {
        disposables.add(disposable)
    }
}