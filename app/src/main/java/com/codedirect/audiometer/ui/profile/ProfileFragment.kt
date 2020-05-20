package com.codedirect.audiometer.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codedirect.audiometer.R
import com.codedirect.audiometer.databinding.FragmentProfileBinding
import com.codedirect.audiometer.utils.SessionManager
import com.codedirect.audiometer.utils.common.EventObserver
import com.codedirect.audiometer.utils.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private val model: ProfileViewModel by viewModel()
    private val sessionManager by lazy {
        SessionManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentProfileBinding>(
                inflater,
                R.layout.fragment_profile,
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
        model.openProfile.observe(viewLifecycleOwner, EventObserver {
            sessionManager.setLogin(false)
            findNavController().popBackStack(R.id.dashboardPatientFragment, true)
        })
    }

}
