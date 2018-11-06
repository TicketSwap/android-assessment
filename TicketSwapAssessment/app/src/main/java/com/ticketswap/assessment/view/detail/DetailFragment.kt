package com.ticketswap.assessment.view.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ticketswap.assessment.BaseFragment
import com.ticketswap.assessment.R
import com.ticketswap.assessment.domain.model.ArtistItem
import com.ticketswap.assessment.view.clipboard.CopyUtil
import com.ticketswap.assessment.view.image.ImageLoader
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_detail, container, false)

    private lateinit var viewModel: DetailViewModel

    private var artistId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(DetailViewModel::class.java)
        artistId = arguments?.getString("id")

        if (artistId == null) {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel(viewModel = viewModel)
        viewModel.loadArtistInfoLocal(artistId!!).observe(this, Observer {
            it?.also { loadInfo(it) }
        })
        viewModel.loadArtistInfo(artistId!!)
        viewModel.isLoading.observe(this, Observer {
            progress_detail_progress.visibility = if (it == true) View.VISIBLE else View.GONE
        })
    }

    private fun loadInfo(info: ArtistItem) {
        imageLoader.load(info.image, R.drawable.placeholder, image_view_detail_image)
        text_view_detail_artist_name.text = info.name
        text_view_detail_artist_popularity.text = getString(R.string.artist_popularity, info.popularity)
        button_detail_copy_uri.setOnClickListener {
            CopyUtil.copy(context!!, "uri", info.uri)
            showSnack(getString(R.string.uri_copied_to_clipboard))
        }
    }
}