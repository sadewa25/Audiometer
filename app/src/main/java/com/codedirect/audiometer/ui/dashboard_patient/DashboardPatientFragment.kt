package com.codedirect.audiometer.ui.dashboard_patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.codedirect.audiometer.R
import com.codedirect.audiometer.databinding.FragmentDashboardPatientBinding
import kotlinx.android.synthetic.main.fragment_dashboard_patient.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DashboardPatientFragment : Fragment() {

    private val model: DashboardPatientViewModel by viewModel()
    private val _dashboardPatientAdapter by lazy {
        DashboardPatientAdapter(model)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentDashboardPatientBinding>(
                inflater,
                R.layout.fragment_dashboard_patient,
                container,
                false
            )
        binding.model = model
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRvAdapter()
    }

    private fun setupRvAdapter() {
        rv_dashboard_patient.adapter = _dashboardPatientAdapter
    }

}
