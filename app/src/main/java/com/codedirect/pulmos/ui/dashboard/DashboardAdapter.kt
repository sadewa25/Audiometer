package com.codedirect.pulmos.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.codedirect.pulmos.databinding.RvItemDashboardBinding
import com.codedirect.pulmos.utils.databinding.AppRecyclerView
import com.codedirect.pulmos.utils.models.Menus

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