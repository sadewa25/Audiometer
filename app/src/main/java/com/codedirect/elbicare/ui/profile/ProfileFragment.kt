package com.codedirect.elbicare.ui.profile

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
import com.codedirect.elbicare.databinding.FragmentProfileBinding
import com.codedirect.elbicare.utils.SessionManager
import com.codedirect.elbicare.utils.common.EventObserver
import com.codedirect.elbicare.utils.common.Status
import com.codedirect.elbicare.utils.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private val model: ProfileViewModel by viewModel()
    private val sessionManager by lazy {
        SessionManager(requireContext())
    }
    private var dataMenu: DataItems? = null

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
        binding.lifecycleOwner = this
        binding.menus = dataMenu
        binding.models = model
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getProfile()
        setupObservers()
    }

    private fun setupObservers() {
        model.openProfile.observe(viewLifecycleOwner, EventObserver {
            sessionManager.setLogin(false)
            navigateToSplashScreen()
        })
        model.openChangePassword.observe(viewLifecycleOwner, EventObserver {
            navigateToChangePassword()
        })
    }

    private fun navigateToSplashScreen() {
        val actions = ProfileFragmentDirections.actionProfileFragmentToNavigationGraph()
        findNavController().navigate(actions)
    }

    private fun navigateToChangePassword() {
        val actions = ProfileFragmentDirections.actionProfileFragmentToChangePasswordFragment()
        findNavController().navigate(actions)
    }

    private fun getProfile() {
        model.getProfile(
            DataItems(
                authenticatedId = sessionManager.getIDUser().toString()
            )
        ).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loading_profile.visibility = View.GONE
                        resource.data.let { data -> retrieveList(data) }
                    }
                    Status.ERROR -> {
                        loading_profile.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        loading_profile.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(items: ResponseJSON?) {
        model.setDataProfile(items)
    }


}
