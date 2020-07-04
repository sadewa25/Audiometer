package com.codedirect.pulmos.ui.dashboard.patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codedirect.pulmos.R
import com.codedirect.pulmos.databinding.FragmentDashboardPatientBinding
import com.codedirect.pulmos.ui.dashboard.DashboardPatientAdapter
import com.codedirect.pulmos.ui.dashboard.DashboardViewModel
import com.codedirect.pulmos.utils.SessionManager
import com.codedirect.pulmos.utils.common.EventObserver
import com.codedirect.pulmos.utils.findNavController
import kotlinx.android.synthetic.main.fragment_dashboard_patient.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardPatientFragment : Fragment() {

    private val model: DashboardViewModel by viewModel()
    private val _dashboardPatientAdapter by lazy {
        DashboardPatientAdapter(model)
    }
    private val sessionManager by lazy {
        SessionManager(requireContext())
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

        setupTitle()
        setupRvAdapter()
        setupObservers()
    }

    private fun setupTitle() {
        tv_dashboard_patient.text =
            String.format(getString(R.string.title_hello), sessionManager.getUsername().toString())
    }

    private fun setupObservers() {
        model.openDashboardPatient.observe(viewLifecycleOwner, EventObserver {
            when (it.title) {
                getString(R.string.menu_report) -> navigateToReportSymptoms()
                getString(R.string.menu_history) -> navigateToHistory()
                getString(R.string.menu_profile) -> navigateToProfile()
            }
        })
    }

    private fun navigateToProfile() {
        val actions =
            DashboardPatientFragmentDirections.actionDashboardPatientFragmentToNavigationProfile()
        findNavController().navigate(actions)
    }

    private fun navigateToHistory() {
        val actions =
            DashboardPatientFragmentDirections.actionDashboardPatientFragmentToNavigationHistory()
        findNavController().navigate(actions)
    }

    private fun navigateToReportSymptoms() {
        val actions =
            DashboardPatientFragmentDirections.actionDashboardPatientFragmentToNavigationReport()
        findNavController().navigate(actions)
    }

    private fun setupRvAdapter() {
        rv_dashboard_patient.adapter = _dashboardPatientAdapter
    }

}
