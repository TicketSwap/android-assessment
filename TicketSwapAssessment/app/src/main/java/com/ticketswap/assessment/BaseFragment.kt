package com.ticketswap.assessment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.widget.TextView
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.squareup.moshi.Moshi
import com.ticketswap.assessment.domain.model.SpotifyError
import com.ticketswap.assessment.network.client.HttpException
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {

    @Inject
    lateinit var moshi: Moshi

    protected fun showSnack(error: String?) {
        if (error != null) Snackbar.make(view!!, error, Snackbar.LENGTH_LONG).apply {
            this.view.setBackgroundColor(ContextCompat.getColor(context, R.color.spotify_green))
            this.view.findViewById<TextView>(android.support.design.R.id.snackbar_text).apply {
                setTextColor(ContextCompat.getColor(context, R.color.white))
            }
        }.show()
    }

    protected fun bindViewModel(viewModel: BaseViewModel) {
        viewModel.errorLiveData.observe(this, Observer {
            (it as? HttpException)?.let {
                if (it.code == 401) {
                    findNavController().navigate(R.id.loginFragment, Bundle().apply {
                        putBoolean("clear", true)
                    },
                            NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
                    )
                }

                moshi.adapter(SpotifyError::class.java).fromJson(it.body)?.apply {
                    showSnack(error?.message)
                }
            }

        })
    }
}