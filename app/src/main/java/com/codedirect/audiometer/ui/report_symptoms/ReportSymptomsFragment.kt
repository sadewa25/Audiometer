package com.codedirect.audiometer.ui.report_symptoms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.codedirect.audiometer.R
import com.codedirect.audiometer.data.source.remote.response.DataItems
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.databinding.FragmentReportSymptomsBinding
import com.codedirect.audiometer.utils.SessionManager
import com.codedirect.audiometer.utils.Utils
import com.codedirect.audiometer.utils.common.EventObserver
import com.codedirect.audiometer.utils.common.Status
import com.codedirect.audiometer.utils.findNavController
import kotlinx.android.synthetic.main.fragment_report_symptoms.*
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
    private val sessionManager by lazy {
        SessionManager(requireContext())
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
                createSymptoms(
                    DataItems(
                        authenticatedId = sessionManager.getIDUser().toString(),
                        batuk = requireActivity().findViewById<RadioButton>(model.getCoughOptionsSelected()).text.toString(),
                        demam = requireActivity().findViewById<RadioButton>(model.getfeverOptionsSelected()).text.toString(),
                        lemas = requireActivity().findViewById<RadioButton>(model.getlimpOptionsSelected()).text.toString(),
                        mual = requireActivity().findViewById<RadioButton>(model.getnauseaOptionsSelected()).text.toString(),
                        pusing = requireActivity().findViewById<RadioButton>(model.getheadacheOptionsSelected()).text.toString(),
                        sesak = requireActivity().findViewById<RadioButton>(model.getbreathlessOptionsSelected()).text.toString()
                    )
                )
            }

        })
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
        if (data?.status == getString(R.string.success_).toInt())
            findNavController().popBackStack(R.id.reportPatientFragment, true)
    }


}
