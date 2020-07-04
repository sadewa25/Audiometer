package com.codedirect.pulmos.ui.patient_companion

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
import com.codedirect.pulmos.databinding.FragmentPatientCompanionBinding
import com.codedirect.pulmos.utils.SessionManager
import com.codedirect.pulmos.utils.common.Status
import kotlinx.android.synthetic.main.fragment_patient_companion.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PatientCompanionFragment : Fragment() {

    private val model: PatientCompanionViewModel by viewModel()
    private val sessionManager by lazy {
        SessionManager(requireContext())
    }
    val _dataPatientAdapter by lazy {
        PatientCompanionAdapter(model)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentPatientCompanionBinding>(
                inflater,
                R.layout.fragment_patient_companion,
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
        rv_patient_companion.adapter = _dataPatientAdapter
    }

    private fun setupLoadData() {
        model.getDataPatintByCompanion(
            DataItems(authenticatedId = sessionManager.getIDUser().toString())
        ).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loading_patient_companion.visibility = View.GONE
                        resource.data.let { data -> retrieveList(data) }
                    }
                    Status.ERROR -> {
                        loading_patient_companion.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        loading_patient_companion.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(data: ResponseJSON?) {
        model.setDataPatient(data)
        _dataPatientAdapter.notifyDataSetChanged()
    }

}
