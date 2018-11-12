package com.ticketswap.assessment

import android.arch.lifecycle.ViewModel
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import android.support.v7.widget.RecyclerView


abstract class BaseBindingAdapter(val viewModel: ViewModel) : RecyclerView.Adapter<BaseBindingAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        setViewModel(binding)
        return MyViewHolder(viewModel, binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj = getObjForPosition(position) as String
        holder.bind(obj)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    protected abstract fun getObjForPosition(position: Int): Any

    protected abstract fun getLayoutIdForPosition(position: Int): Int

    protected abstract fun setViewModel(binding: ViewDataBinding)

    class MyViewHolder(val viewModel: ViewModel, val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(name : String) {
            binding.setVariable(BR.title, name)
            binding.executePendingBindings()
        }
    }
}