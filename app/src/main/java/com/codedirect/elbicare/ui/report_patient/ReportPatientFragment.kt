package com.codedirect.elbicare.ui.report_patient

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
import com.codedirect.elbicare.databinding.FragmentReportPatientBinding
import com.codedirect.elbicare.utils.*
import com.codedirect.elbicare.utils.common.EventObserver
import com.codedirect.elbicare.utils.common.Status
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
        hideToolbar()
        setupObservers()
        getStatus()
        loadProfile(sessionManager.getIDUser().toString())
    }

    private fun loadProfile(idUser: String) {
        model.loadProfile(
            DataItems(
                authenticatedId = sessionManager.getIDUser().toString()
            )
        ).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loading_report_patient.visibility = View.GONE
                        resource.data.let { data -> retrieveResponseProfile(data) }
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

    private fun retrieveResponseProfile(data: ResponseJSON?) {
        if (data != null || !data?.data.isNullOrEmpty()){
            tv_report_patient_condition_content.text = data?.data?.get(0)?.statusMinum.toString()
            tv_report_patient_status.text = String.format(getString(R.string.title_skip_drugs),data?.data?.get(0)?.jumlahObatLewat)
            tv_report_patient_drugs.text = String.format(getString(R.string.title_drink_drugs),data?.data?.get(0)?.jumlahObatMinum)
        }
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
        } else {
            findNavController().navigateUp()
        }
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
