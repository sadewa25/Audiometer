package com.codedirect.elbicare.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codedirect.elbicare.R
import com.codedirect.elbicare.databinding.FragmentHistoryBinding
import com.codedirect.elbicare.utils.common.EventObserver
import com.codedirect.elbicare.utils.findNavController
import kotlinx.android.synthetic.main.fragment_history.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    private val model: HistoryViewModel by viewModel()
    private val _historyPatientAdapter by lazy {
        HistoryAdapter(model)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentHistoryBinding>(
                inflater,
                R.layout.fragment_history,
                container,
                false
            )
        binding.model = model
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRvAdapter()
        setupObservers()
    }

    private fun setupObservers() {
        model.openHistoryReportPatient.observe(viewLifecycleOwner, EventObserver {
            when (it.title) {
                getString(R.string.reporting_symptoms) -> navigateToHistoryReportSymptoms()
                getString(R.string.reporting_needed) -> navigateToHistoryReportNeeded()
            }
        })
    }

    private fun navigateToHistoryReportNeeded() {
        val actions = HistoryFragmentDirections.actionHistoryFragmentToHistoryReportNeededFragment()
        findNavController().navigate(actions)
    }

    private fun navigateToHistoryReportSymptoms() {
        val actions =
            HistoryFragmentDirections.actionHistoryFragmentToHistoryReportSymptomsFragment()
        findNavController().navigate(actions)
    }

    private fun setupRvAdapter() {
        rv_history_report_patient.adapter = _historyPatientAdapter
    }

}
