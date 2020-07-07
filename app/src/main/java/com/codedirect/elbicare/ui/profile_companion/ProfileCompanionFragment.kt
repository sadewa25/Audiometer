package com.codedirect.elbicare.ui.profile_companion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codedirect.elbicare.R
import com.codedirect.elbicare.databinding.FragmentProfileCompanionBinding
import com.codedirect.elbicare.utils.SessionManager
import com.codedirect.elbicare.utils.common.EventObserver
import com.codedirect.elbicare.utils.findNavController
import kotlinx.android.synthetic.main.fragment_profile_companion.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileCompanionFragment : Fragment() {

    private val model: ProfileCompanionViewModel by viewModel()
    private val sessionManager by lazy {
        SessionManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentProfileCompanionBinding>(
                inflater,
                R.layout.fragment_profile_companion,
                container,
                false
            )
        binding.lifecycleOwner = this
        binding.models = model
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUsername()
        setupObservers()
    }

    private fun setupUsername() {
        tv_profile_companion_companion_name_content.text = sessionManager.getUsername().toString()
    }

    private fun setupObservers() {
        model.openProfile.observe(viewLifecycleOwner, EventObserver {
            sessionManager.setLogin(false)
            findNavController().popBackStack(R.id.dashboardCompanionFragment, true)
        })
        model.openChangePassword.observe(viewLifecycleOwner, EventObserver {
            navigateToChangePassword()
        })
    }

    private fun navigateToChangePassword() {
        val actions =
            ProfileCompanionFragmentDirections.actionProfileCompanionFragmentToChangePasswordFragment2()
        findNavController().navigate(actions)
    }

}
