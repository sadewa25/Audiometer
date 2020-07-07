package com.codedirect.elbicare.utils.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class AppRecyclerView<VM : ViewModel, BIND : ViewDataBinding, M : Any>(
    private val viewModel: VM,
    private val diffUtil: DiffUtil.ItemCallback<M>
) : ListAdapter<M, AppRecyclerView.ViewHolder<BIND>>(diffUtil), BindableAdapter<M> {

    abstract fun onCreateViewBindingHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder<BIND>

    /**
     * Used to set attribute on viewBinding, no need to call executePendingBindings()
     */
    abstract fun onPrepareBindViewHolder(binding: BIND, viewModel: VM, model: M)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<BIND> {
        val inflater = LayoutInflater.from(parent.context)
        return onCreateViewBindingHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ViewHolder<BIND>, position: Int) {
        val item = getItem(position)
        onPrepareBindViewHolder(holder.binding, viewModel, item)
        holder.binding.executePendingBindings()
    }

    override fun applyList(data: List<M>) {
        submitList(data)
    }

    class ViewHolder<BIND : ViewDataBinding> constructor(val binding: BIND) :
        RecyclerView.ViewHolder(binding.root)
}
