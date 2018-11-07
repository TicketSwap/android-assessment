package com.ticketswap.assessment.view.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ticketswap.assessment.BaseFragment
import com.ticketswap.assessment.R
import com.ticketswap.assessment.spotify.SpotifyLoginHelper
import kotlinx.android.synthetic.main.fragment_login.*
import javax.inject.Inject

class LoginFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_login, container, false)

    private lateinit var loginHelperViewModel: LoginHelperViewModel

    private var clearUserInfo: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)
        activity?.run {
            loginHelperViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginHelperViewModel::class.java)
        }
        clearUserInfo = arguments?.getBoolean("clear", false) ?: false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel.clearDatabase(clearUserInfo)
        button_login_spotify.setOnClickListener {
            loginViewModel.loginClicked()
        }

        loginViewModel.navigateToSearch.observe(this, Observer {
            findNavController().navigate(R.id.searchFragment,
                    null, NavOptions.Builder().setPopUpTo(R.id.loginFragment, true)
                    .build())
        })

        loginViewModel.isLoginClicked.observe(this, Observer {
            SpotifyLoginHelper.requestLoginActivity(activity!!)
        })

        loginHelperViewModel.navigateToSearch.observe(this, Observer {
            findNavController().navigate(R.id.searchFragment,
                    null, NavOptions.Builder().setPopUpTo(R.id.loginFragment, true)
                    .build())
        })
    }

}