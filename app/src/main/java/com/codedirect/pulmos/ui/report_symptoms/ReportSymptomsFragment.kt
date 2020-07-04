package com.codedirect.pulmos.ui.report_symptoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.codedirect.pulmos.R
import com.codedirect.pulmos.data.source.remote.response.DataItems
import com.codedirect.pulmos.data.source.remote.response.ResponseJSON
import com.codedirect.pulmos.databinding.FragmentReportSymptomsBinding
import com.codedirect.pulmos.utils.SessionManager
import com.codedirect.pulmos.utils.Utils
import com.codedirect.pulmos.utils.common.EventObserver
import com.codedirect.pulmos.utils.common.Status
import com.codedirect.pulmos.utils.findNavController
import com.codedirect.pulmos.utils.nameValidation
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_report_symptoms.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReportSymptomsFragment : BottomSheetDialogFragment() {

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
    private val sessionManager by lazy {
        SessionManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun checkEmpty(): Boolean {
        return nameValidation(ed_report_symptoms.text.toString(), ti_report_symptoms)
    }

    private fun setupObservers() {
        model.openReportSymptoms.observe(viewLifecycleOwner, EventObserver {
            if (checkEmpty()) {
                Utils().formDialog(
                    requireContext(),
                    getString(R.string.information),
                    getString(R.string.confirm_send)
                ) {
                    if (sessionManager.getRoleUser()
                            .equals(getString(R.string.id_user_type_patient))
                    )
                        postSymptomsPatient()
                    else
                        postSymptomsCompanion()
                }
            }
        })
    }

    private fun postSymptomsCompanion() {
        createCompanionReportSymptoms(
            DataItems(
                authenticatedId = sessionManager.getIDUser().toString(),
                gejala = ed_report_symptoms.text.toString()
            )
        )
    }

    private fun createCompanionReportSymptoms(dataItems: DataItems) {
        model.createCompanionReportSymptoms(
            dataItems
        ).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loading_report_symptoms.visibility = View.GONE
                        resource.data.let { data -> retrieveReturn(data) }
                    }
                    Status.ERROR -> {
                        loading_report_symptoms.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        loading_report_symptoms.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun postSymptomsPatient() {
        createSymptoms(
            DataItems(
                authenticatedId = sessionManager.getIDUser().toString(),
                gejala = ed_report_symptoms.text.toString()
            )
        )
    }

    private fun createSymptoms(data: DataItems) {
        model.createReportSymptoms(
            data
        ).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loading_report_symptoms.visibility = View.GONE
                        resource.data.let { data -> retrieveReturn(data) }
                    }
                    Status.ERROR -> {
                        loading_report_symptoms.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        loading_report_symptoms.visibility = View.VISIBLE
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
