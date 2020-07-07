package com.codedirect.elbicare.ui.history.report_needed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.codedirect.elbicare.R
import com.codedirect.elbicare.data.source.remote.response.DataItems
import com.codedirect.elbicare.data.source.remote.response.ResponseJSON
import com.codedirect.elbicare.databinding.FragmentHistoryReportNeededBinding
import com.codedirect.elbicare.ui.history.HistoryViewModel
import com.codedirect.elbicare.utils.SessionManager
import com.codedirect.elbicare.utils.common.Status
import kotlinx.android.synthetic.main.fragment_history_report_needed.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryReportNeededFragment : Fragment() {

    private val model: HistoryViewModel by viewModel()
    private val sessionManager by lazy {
        SessionManager(requireContext())
    }
    val _historyReportNeededAdapter by lazy {
        HistoryReportNeededAdapter(model)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentHistoryReportNeededBinding>(
                inflater,
                R.layout.fragment_history_report_needed,
                container,
                false
            )
        binding.model = model
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLoadData()
        setupRvAdapter()
    }


    private fun setupRvAdapter() {
        rv_history_report_needed.adapter = _historyReportNeededAdapter
    }


    private fun setupLoadData() {
        model.getReportNeededByPatient(
            DataItems(authenticatedId = sessionManager.getIDUser().toString())
        ).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loading_history_report_needed.visibility = View.GONE
                        resource.data.let { data -> retrieveList(data) }
                    }
                    Status.ERROR -> {
                        loading_history_report_needed.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        loading_history_report_needed.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(data: ResponseJSON?) {
        model.setDataReportNeeded(data)
        _historyReportNeededAdapter.notifyDataSetChanged()
    }

}
