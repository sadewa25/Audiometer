package com.codedirect.audiometer.ui.dashboard_patient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.codedirect.audiometer.databinding.RvItemDashboardBinding
import com.codedirect.audiometer.utils.databinding.AppRecyclerView
import com.codedirect.audiometer.utils.models.Menus

class DashboardPatientAdapter(viewModel: DashboardPatientViewModel) :
    AppRecyclerView<DashboardPatientViewModel, RvItemDashboardBinding, Menus>(
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
        viewModel: DashboardPatientViewModel,
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