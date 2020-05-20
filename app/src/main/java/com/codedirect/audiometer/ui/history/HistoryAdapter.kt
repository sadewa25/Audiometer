package com.codedirect.audiometer.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.codedirect.audiometer.databinding.RvItemHistoryBinding
import com.codedirect.audiometer.utils.databinding.AppRecyclerView
import com.codedirect.audiometer.utils.models.Menus

class HistoryAdapter(viewModel: HistoryViewModel) :
    AppRecyclerView<HistoryViewModel, RvItemHistoryBinding, Menus>(
        viewModel,
        DiffUtilDrug()
    ) {

    override fun onCreateViewBindingHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder<RvItemHistoryBinding> =
        ViewHolder(RvItemHistoryBinding.inflate(inflater, parent, false))

    override fun onPrepareBindViewHolder(
        binding: RvItemHistoryBinding,
        viewModel: HistoryViewModel,
        model: Menus
    ) {
        binding.menus = model
        binding.model = viewModel
    }

}

class DiffUtilDrug : DiffUtil.ItemCallback<Menus>() {

    override fun areItemsTheSame(oldItem: Menus, newItem: Menus): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Menus, newItem: Menus): Boolean {
        return oldItem.title == newItem.title
    }

}
