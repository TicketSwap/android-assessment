package com.ticketswap.assessment

import android.os.Bundle
import androidx.navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean =
            findNavController(R.id.nav_host_fragment).navigateUp()

}
