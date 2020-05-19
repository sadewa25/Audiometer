package com.codedirect.audiometer.ui.report_patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codedirect.audiometer.R
import com.codedirect.audiometer.databinding.FragmentReportPatientBinding
import com.codedirect.audiometer.utils.common.EventObserver
import com.codedirect.audiometer.utils.findNavController
import kotlinx.android.synthetic.main.fragment_report_patient.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReportPatientFragment : Fragment() {

    private val model: ReportPatientViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentReportPatientBinding>(
                inflater,
                R.layout.fragment_report_patient,
                container,
                false
            )
        binding.model = model
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        getStatus()
    }

    private fun setupObservers() {
        model.openReportPatientSymptoms.observe(viewLifecycleOwner, EventObserver {
            navigateToReportSymptoms()
        })
        model.openReportPatientNeeded.observe(viewLifecycleOwner, EventObserver {
            navigateToReportNeeded()
        })
    }

    private fun navigateToReportNeeded() {
        val actions =
            ReportPatientFragmentDirections.actionReportPatientFragmentToReportNeededFragment()
        findNavController().navigate(actions)
    }

    private fun navigateToReportSymptoms() {
        val actions =
            ReportPatientFragmentDirections.actionReportPatientFragmentToReportSymptomsFragment()
        findNavController().navigate(actions)
    }

    private fun getStatus() {
        tv_report_patient_status.text = resources.getString(R.string.title_status, "14 Hari")
        tv_report_patient_drugs.text = resources.getString(R.string.title_skip_drugs, "0")
    }

}
