package com.ticketswap.assessment

import android.databinding.ViewDataBinding
import com.ticketswap.assessment.viewmodel.SpotifySearchViewModel

class SingleLayoutAdapter(viewModel: SpotifySearchViewModel, val layoutId: Int) : BaseBindingAdapter(viewModel) {

    override fun setViewModel(binding: ViewDataBinding) {
        binding.setVariable(BR.viewModel, viewModel)
    }

    var items = listOf<String>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getObjForPosition(position: Int): Any {
        return items.get(position)
    }

    override fun getLayoutIdForPosition(position: Int): Int {
        return layoutId
    }
}