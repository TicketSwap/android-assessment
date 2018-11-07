package com.ticketswap.assessment.view.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ticketswap.assessment.BaseFragment
import com.ticketswap.assessment.R
import com.ticketswap.assessment.view.image.ImageLoader
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : BaseFragment() {

    @Inject
    lateinit var facotry: ViewModelProvider.Factory
    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var searchViewModel: SearchViewModel

    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel = ViewModelProviders.of(this, facotry).get(SearchViewModel::class.java)
        adapter = SearchAdapter(imageLoader, R.drawable.placeholder, {
            itemClicked(it)
        }, {
            if (it) empty_retry_search_view.showEmpty() else empty_retry_search_view.hideEmpty()
        })
    }

    private fun itemClicked(item: SearchAdapterItem) {
        findNavController().navigate(R.id.detailFragment, Bundle().apply {
            putString("id", item.id)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewModel(searchViewModel)
        edit_text_search_input.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                ContextCompat.getDrawable(context!!, R.drawable.ic_search), null)
        edit_text_search_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                observeArtists(searchViewModel.changeObserver(p0.toString()))
                searchViewModel.getArtistsFromCloud(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        recycler_view_search_list.adapter = adapter
        recycler_view_search_list.layoutManager = LinearLayoutManager(context)

        empty_retry_search_view.showEmpty()
    }

    private var data: LiveData<List<SearchAdapterItem>>? = null

    /**
     * remove any observer for live data, change the live data and add new observer
     */
    private fun observeArtists(observable: LiveData<List<SearchAdapterItem>>) {
        this.data?.removeObservers(this)
        this.data = observable
        this.data?.observe(this, Observer {
            it?.let {
                adapter.updateItems(it)
            }
        })
    }

}