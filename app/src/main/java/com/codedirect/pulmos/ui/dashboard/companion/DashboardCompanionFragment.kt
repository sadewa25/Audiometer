package com.codedirect.pulmos.ui.dashboard.companion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.codedirect.pulmos.R
import com.codedirect.pulmos.databinding.FragmentDashboardCompanionBinding
import com.codedirect.pulmos.ui.dashboard.DashboardPatientAdapter
import com.codedirect.pulmos.ui.dashboard.DashboardViewModel
import com.codedirect.pulmos.utils.SessionManager
import com.codedirect.pulmos.utils.common.EventObserver
import com.codedirect.pulmos.utils.findNavController
import kotlinx.android.synthetic.main.fragment_dashboard_companion.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardCompanionFragment : Fragment() {

    private val model: DashboardViewModel by viewModel()
    private val _dashboardPatientAdapter by lazy {
        DashboardPatientAdapter(model)
    }
    private val sessionManager by lazy {
        SessionManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentDashboardCompanionBinding>(
                inflater,
                R.layout.fragment_dashboard_companion,
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

    private fun setupObservers() {
        model.openDashboardPatient.observe(viewLifecycleOwner, EventObserver {
            when (it.title) {
                getString(R.string.menu_report) -> navigateToReport()
                getString(R.string.menu_patient) -> navigateToPatient()
                getString(R.string.menu_profile) -> navigateToProfile()
            }
        })
    }

    private fun navigateToPatient() {
        val actions =
            DashboardCompanionFragmentDirections.actionDashboardCompanionFragmentToPatientCompanionFragment()
        findNavController().navigate(actions)
    }

    private fun navigateToProfile() {
        val actions =
            DashboardCompanionFragmentDirections.actionDashboardCompanionFragmentToProfileCompanionFragment()
        findNavController().navigate(actions)
    }

    private fun navigateToReport() {
        val myItems = listOf(getString(R.string.report_symptoms), getString(R.string.report_needed))
        MaterialDialog(requireContext()).show {
            listItems(items = myItems) { dialog, index, text ->
                when (index) {
                    0 -> navigateToReportSymptoms()
                    1 -> navigateToReportNeeded()
                }
            }
        }
    }

    private fun navigateToReportNeeded() {
        val actions =
            DashboardCompanionFragmentDirections.actionDashboardCompanionFragmentToReportNeededFragment2()
        findNavController().navigate(actions)
    }

    private fun navigateToReportSymptoms() {
        val actions =
            DashboardCompanionFragmentDirections.actionDashboardCompanionFragmentToReportSymptomsFragment2()
        findNavController().navigate(actions)
    }

    private fun setupRvAdapter() {
        rv_dashboard_companion.adapter = _dashboardPatientAdapter
    }

    private fun setupTitle() {
        tv_dashboard_companion.text =
            String.format(getString(R.string.title_hello), sessionManager.getUsername().toString())
    }

}
