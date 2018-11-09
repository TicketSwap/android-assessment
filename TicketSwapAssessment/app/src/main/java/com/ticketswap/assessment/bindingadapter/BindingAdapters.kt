package com.ticketswap.assessment.bindingadapter

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.BindingAdapter
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.ticketswap.assessment.RecyclerAdapter
import com.ticketswap.assessment.extensions.getParentActivity

@BindingAdapter("mutableText")
fun setMutableEditText(view: EditText, text: MutableLiveData<String>?) {

    val watcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { }
        override fun afterTextChanged(s: Editable) {
            text?.postValue(s.toString())
        }
    }
    // TODO: the following registration doesn't include a lifecycle ownwer, this means that it's prone to memory leaks
    view.addTextChangedListener(watcher)
}

@BindingAdapter("populate")
fun setAdapter(view: RecyclerView, adapter: MutableLiveData<RecyclerAdapter>) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if (adapter == null) {
        return
    }
    view.adapter = adapter.value
    view.layoutManager = LinearLayoutManager(parentActivity)
    if(parentActivity != null) {
        adapter.observe(parentActivity, Observer {
            value -> (view.adapter as RecyclerAdapter).notifyDataSetChanged()
        })
    }
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, error : MutableLiveData<String>) {
    val parentActivity:AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && error != null) {
        error.observe(parentActivity, Observer {
            value -> view.visibility = if (hasError(value)) View.VISIBLE else View.GONE
        })
    }
}

private fun hasError(error : String?) : Boolean {
    return error != null && !error.isEmpty()
}