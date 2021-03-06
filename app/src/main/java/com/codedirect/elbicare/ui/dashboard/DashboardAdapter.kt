package com.codedirect.elbicare.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.codedirect.elbicare.databinding.RvItemDashboardBinding
import com.codedirect.elbicare.utils.databinding.AppRecyclerView
import com.codedirect.elbicare.utils.models.Menus

class DashboardPatientAdapter(viewModel: DashboardViewModel) :
    AppRecyclerView<DashboardViewModel, RvItemDashboardBinding, Menus>(
        viewModel,
        DiffUtilDrug()
    ) {

    override fun onCreateViewBindingHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder<RvItemDashboardBinding> =
        ViewHolder(RvItemDashboardBinding.inflate(inflater, parent, false))

    override fun onPrepareBindViewHolder(
        binding: RvItemDashboardBinding,
        viewModel: DashboardViewModel,
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