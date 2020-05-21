package com.codedirect.audiometer.ui.report_needed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.codedirect.audiometer.R
import com.codedirect.audiometer.data.source.remote.response.DataItems
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.databinding.FragmentReportNeededBinding
import com.codedirect.audiometer.utils.SessionManager
import com.codedirect.audiometer.utils.Utils
import com.codedirect.audiometer.utils.common.EventObserver
import com.codedirect.audiometer.utils.common.Status
import com.codedirect.audiometer.utils.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_report_needed.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReportNeededFragment : BottomSheetDialogFragment() {

    private val model: ReportNeededViewModel by viewModel()
    private val sessionManager by lazy {
        SessionManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentReportNeededBinding>(
                inflater,
                R.layout.fragment_report_needed,
                container,
                false
            )
        binding.model = model
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        model.openReportSymptoms.observe(viewLifecycleOwner, EventObserver {
            Utils().formDialog(
                requireContext(),
                getString(R.string.information),
                getString(R.string.confirm_send)
            ) {
                if (sessionManager.getRoleUser().equals(getString(R.string.id_user_type_patient)))
                    createNeeded(
                        DataItems(
                            authenticatedId = sessionManager.getIDUser().toString(),
                            jenisKebutuhan = ed_report_needed_kind.text.toString(),
                            keterangan = ed_report_needed_desc.text.toString()
                        )
                    )
                else
                    createCompanionNeeded(
                        DataItems(
                            authenticatedId = sessionManager.getIDUser().toString(),
                            jenisKebutuhan = ed_report_needed_kind.text.toString(),
                            keterangan = ed_report_needed_desc.text.toString()
                        )
                    )
            }
        })
    }

    private fun createCompanionNeeded(dataItems: DataItems) {
        model.createCompanionReportNeeded(
            dataItems
        ).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loading_report_needed.visibility = View.GONE
                        resource.data.let { data -> retrieveReturn(data) }
                    }
                    Status.ERROR -> {
                        loading_report_needed.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        loading_report_needed.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun createNeeded(data: DataItems) {
        model.createReportNeeded(
            data
        ).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loading_report_needed.visibility = View.GONE
                        resource.data.let { data -> retrieveReturn(data) }
                    }
                    Status.ERROR -> {
                        loading_report_needed.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        loading_report_needed.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveReturn(data: ResponseJSON?) {
        Utils().toast(requireContext(), data?.message.toString())
        if (data?.status == getString(R.string.success_).toInt()) {
            if (sessionManager.getRoleUser().equals(getString(R.string.id_user_type_patient)))
                findNavController().popBackStack(R.id.reportPatientFragment, true)
            else
                findNavController().navigateUp()
        }
    }

}
