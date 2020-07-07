package com.codedirect.elbicare.ui.history.report_needed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.codedirect.elbicare.data.source.remote.response.DataItems
import com.codedirect.elbicare.databinding.RvItemHistoryReportNeededBinding
import com.codedirect.elbicare.ui.history.HistoryViewModel
import com.codedirect.elbicare.utils.databinding.AppRecyclerView

class HistoryReportNeededAdapter(viewModel: HistoryViewModel) :
    AppRecyclerView<HistoryViewModel, RvItemHistoryReportNeededBinding, DataItems>(
        viewModel,
        DiffUtilDrugs()
    ) {

    override fun onCreateViewBindingHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder<RvItemHistoryReportNeededBinding> =
        ViewHolder(RvItemHistoryReportNeededBinding.inflate(inflater, parent, false))

    override fun onPrepareBindViewHolder(
        binding: RvItemHistoryReportNeededBinding,
        viewModel: HistoryViewModel,
        model: DataItems
    ) {
        binding.menus = model
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