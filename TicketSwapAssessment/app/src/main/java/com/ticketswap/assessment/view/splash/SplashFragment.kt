package com.ticketswap.assessment.view.splash

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ticketswap.assessment.BaseFragment
import com.ticketswap.assessment.R

class SplashFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_splash, container, false)

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        splashViewModel.isUserAuthenticated.observe(this, Observer { authenticated ->
            authenticated?.also {
                if (it) {
                    findNavController().navigate(R.id.loginFragment)
                    return@Observer
                }
                findNavController().navigate(R.id.searchFragment)
            }
        })
    }
}