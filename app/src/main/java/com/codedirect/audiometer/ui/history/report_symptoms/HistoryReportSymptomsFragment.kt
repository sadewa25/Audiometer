package com.codedirect.audiometer.ui.history.report_symptoms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.codedirect.audiometer.R
import com.codedirect.audiometer.data.source.remote.response.DataItems
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.databinding.FragmentHistoryReportSymptomsBinding
import com.codedirect.audiometer.ui.history.HistoryReportAdapter
import com.codedirect.audiometer.ui.history.HistoryViewModel
import com.codedirect.audiometer.utils.SessionManager
import com.codedirect.audiometer.utils.common.Status
import kotlinx.android.synthetic.main.fragment_history_report_symptoms.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryReportSymptomsFragment : Fragment() {

    private val model: HistoryViewModel by viewModel()
    private val sessionManager by lazy {
        SessionManager(requireContext())
    }
    val _historyReportSymptomsAdapter by lazy {
        HistoryReportAdapter(model)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentHistoryReportSymptomsBinding>(
                inflater,
                R.layout.fragment_history_report_symptoms,
                container,
                false
            )
        binding.model = model
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRvAdapter()
        setupLoadData()
    }

    private fun setupRvAdapter() {
        rv_history_report_symptoms.adapter = _historyReportSymptomsAdapter
    }

    private fun setupLoadData() {
        model.getReportSymptomsByPatient(
            DataItems(authenticatedId = sessionManager.getIDUser().toString())
        ).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loading_history_report_symptoms.visibility = View.GONE
                        resource.data.let { data -> retrieveList(data) }
                    }
                    Status.ERROR -> {
                        loading_history_report_symptoms.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        loading_history_report_symptoms.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(data: ResponseJSON?) {
        model.setDataReportSymptoms(data)
        Log.i("Informasi :: ", "oi")
        model.dataReportSymptoms.observe(viewLifecycleOwner, Observer {
            Log.i("Informasi :: ", it.toString())
        })
        _historyReportSymptomsAdapter.notifyDataSetChanged()
    }
}
