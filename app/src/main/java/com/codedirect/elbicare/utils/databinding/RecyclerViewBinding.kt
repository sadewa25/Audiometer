package com.codedirect.elbicare.utils.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

interface BindableAdapter<T : Any> {

    fun applyList(data: List<T>)

}

@BindingAdapter("items")
fun RecyclerView.setItems(items: List<Any>?) {
    if (adapter != null) (adapter as BindableAdapter<Any>).applyList(items ?: listOf())
}