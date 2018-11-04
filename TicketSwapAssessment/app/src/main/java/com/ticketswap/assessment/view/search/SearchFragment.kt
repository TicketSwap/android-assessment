package com.ticketswap.assessment.view.search

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ticketswap.assessment.BaseFragment
import com.ticketswap.assessment.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : BaseFragment() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        edit_text_search_input.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null,
                ContextCompat.getDrawable(context!!, R.drawable.ic_search), null)
        edit_text_search_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                searchViewModel.searchInputChanged(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

}