package com.ticketswap.assessment.view.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = viewModelFactory.create(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_login_spotify.setOnClickListener {
            loginViewModel.loginClicked()
        }

        loginViewModel.navigateToSearch.observe(this, Observer {
            findNavController().navigate(R.id.searchFragment)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val spotifyLogin = SpotifyLoginHelper(activity!!, requestCode, resultCode, data)
        val response = spotifyLogin.extractData()
        loginViewModel.checkLoginStatus(response)
    }
}