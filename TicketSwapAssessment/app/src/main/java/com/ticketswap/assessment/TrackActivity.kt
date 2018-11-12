package com.ticketswap.assessment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.ticketswap.assessment.databinding.ActivityTrackBinding
import com.ticketswap.assessment.entity.TrackEntity
import com.ticketswap.assessment.viewmodel.TrackDetailViewModel


class TrackActivity : AbstractDetailActivity<TrackEntity>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val track = intent.extras.getParcelable<TrackEntity>("track")  <-- didn't work out
        val track = IntentFactory(TrackEntity::class.java).fromJson(intent)
        val factory = TrackDetailViewModel.TrackViewModelFactory(track)
        val viewModel = ViewModelProviders.of(this, factory).get(TrackDetailViewModel::class.java)
        val binding : ActivityTrackBinding = DataBindingUtil.setContentView(this, R.layout.activity_track)
        binding.viewModel = viewModel
    }
}
