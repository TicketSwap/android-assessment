package com.ticketswap.assessment

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.ticketswap.assessment.databinding.ActivityArtistBinding
import com.ticketswap.assessment.entity.ArtistEntity
import com.ticketswap.assessment.viewmodel.ArtistDetailViewModel

class ArtistActivity : AbstractDetailActivity<ArtistEntity>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val artist = intent.extras.getParcelable<ArtistEntity>("artist") <-- didn't work out
        val artist = IntentFactory(ArtistEntity::class.java).fromJson(intent)
        val factory = ArtistDetailViewModel.ArtistViewModelFactory(artist);
        val viewModel = ViewModelProviders.of(this, factory).get(ArtistDetailViewModel::class.java)
        val binding : ActivityArtistBinding = DataBindingUtil.setContentView(this, R.layout.activity_artist)
        binding.setViewModel(viewModel)
    }
}
