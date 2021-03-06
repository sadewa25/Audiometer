package com.codedirect.elbicare.ui.patient_companion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.codedirect.elbicare.data.source.remote.response.DataItems
import com.codedirect.elbicare.databinding.RvItemPatientBinding
import com.codedirect.elbicare.utils.databinding.AppRecyclerView

class PatientCompanionAdapter(viewModel: PatientCompanionViewModel) :
    AppRecyclerView<PatientCompanionViewModel, RvItemPatientBinding, DataItems>(
        viewModel,
        DiffUtilDrugs()
    ) {

    override fun onCreateViewBindingHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): ViewHolder<RvItemPatientBinding> =
        ViewHolder(RvItemPatientBinding.inflate(inflater, parent, false))

    override fun onPrepareBindViewHolder(
        binding: RvItemPatientBinding,
        viewModel: PatientCompanionViewModel,
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