package com.codedirect.audiometer.ui.report_patient.report_symptoms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codedirect.audiometer.R
import com.codedirect.audiometer.databinding.FragmentReportSymptomsBinding
import com.codedirect.audiometer.utils.common.EventObserver
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReportSymptomsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentReportSymptomsBinding>(
                inflater,
                R.layout.fragment_report_symptoms,
                container,
                false
            )
        binding.model = model
        return binding.root
    }

    private val model: ReportSymptomsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        model.openReportSymptoms.observe(viewLifecycleOwner, EventObserver {
            val dataCough = requireActivity().findViewById<RadioButton>(model.getCoughOptionsSelected())

        })
    }

}
