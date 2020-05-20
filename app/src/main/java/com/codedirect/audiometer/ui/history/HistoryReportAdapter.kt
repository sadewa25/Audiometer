package com.codedirect.audiometer.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.codedirect.audiometer.data.source.remote.response.DataItems
import com.codedirect.audiometer.databinding.RvItemHistoryReportBinding
import com.codedirect.audiometer.utils.databinding.AppRecyclerView

class HistoryReportAdapter(viewModel: HistoryViewModel) :
    AppRecyclerView<HistoryViewModel, RvItemHistoryReportBinding, DataItems>(
        viewModel,
        DiffUtilDrugs()
    ) {

    override fun onCreateViewBindingHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder<RvItemHistoryReportBinding> =
        ViewHolder(RvItemHistoryReportBinding.inflate(inflater, parent, false))

    override fun onPrepareBindViewHolder(
        binding: RvItemHistoryReportBinding,
        viewModel: HistoryViewModel,
        model: DataItems
    ) {
        binding.menus = model
        binding.model = viewModel
    }

}

class DiffUtilDrugs : DiffUtil.ItemCallback<DataItems>() {

    override fun areItemsTheSame(oldItem: DataItems, newItem: DataItems): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DataItems, newItem: DataItems): Boolean {
        return oldItem.id == newItem.id
    }

}
