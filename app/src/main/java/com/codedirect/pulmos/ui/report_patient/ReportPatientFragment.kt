package com.codedirect.pulmos.ui.report_patient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.codedirect.pulmos.R
import com.codedirect.pulmos.data.source.remote.response.DataItems
import com.codedirect.pulmos.data.source.remote.response.ResponseJSON
import com.codedirect.pulmos.databinding.FragmentReportPatientBinding
import com.codedirect.pulmos.utils.SessionManager
import com.codedirect.pulmos.utils.common.EventObserver
import com.codedirect.pulmos.utils.common.Status
import com.codedirect.pulmos.utils.confirmationMaterialDialog
import com.codedirect.pulmos.utils.findNavController
import com.codedirect.pulmos.utils.successDialog
import kotlinx.android.synthetic.main.fragment_report_patient.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReportPatientFragment : Fragment() {

    private val model: ReportPatientViewModel by viewModel()
    private val sessionManager by lazy {
        SessionManager(requireContext())
    }

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
        confirmationMaterialDialog(
            getString(R.string.information),
            getString(R.string.confirmation)
        ) {
            model.createReportNeeded(
                DataItems(
                    authenticatedId = sessionManager.getIDUser().toString()
                )
            ).observe(viewLifecycleOwner, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            loading_report_patient.visibility = View.GONE
                            resource.data.let { data -> retrieveResponse(data) }
                        }
                        Status.ERROR -> {
                            loading_report_patient.visibility = View.GONE
                        }
                        Status.LOADING -> {
                            loading_report_patient.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
    }

    private fun retrieveResponse(data: ResponseJSON?) {
        if (data?.status == getString(R.string.success_).toInt()) {
            successDialog(getString(R.string.information), data.message.toString()) {
                findNavController().navigateUp()
                it.dismissWithAnimation()
            }
        } else { findNavController().navigateUp() }
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
