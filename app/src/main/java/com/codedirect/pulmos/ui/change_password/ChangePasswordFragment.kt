package com.codedirect.pulmos.ui.change_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.codedirect.pulmos.R
import com.codedirect.pulmos.data.source.remote.response.DataItems
import com.codedirect.pulmos.data.source.remote.response.ResponseJSON
import com.codedirect.pulmos.databinding.FragmentChangePasswordBinding
import com.codedirect.pulmos.ui.profile.ProfileViewModel
import com.codedirect.pulmos.utils.SessionManager
import com.codedirect.pulmos.utils.Utils
import com.codedirect.pulmos.utils.common.EventObserver
import com.codedirect.pulmos.utils.common.Status
import com.codedirect.pulmos.utils.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_change_password.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangePasswordFragment : BottomSheetDialogFragment() {

    private val model: ProfileViewModel by viewModel()
    private val sessionManager by lazy {
        SessionManager(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentChangePasswordBinding>(
                inflater,
                R.layout.fragment_change_password,
                container,
                false
            )
        binding.lifecycleOwner = this
        binding.models = model
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        model.openSubmitChangePassword.observe(viewLifecycleOwner, EventObserver {
            if (ed_change_password_new.text.isNullOrEmpty() || ed_change_password_old.text.isNullOrEmpty())
                Utils().toast(requireContext(), getString(R.string.there_are_empty_data))
            else
                actionSubmitData()
        })
    }

    private fun actionSubmitData() {
        model.getChangePassword(
            DataItems(
                authenticatedId = sessionManager.getIDUser().toString(),
                old_password = ed_change_password_old.text.toString(),
                new_password = ed_change_password_new.text.toString()
            )
        ).observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loading_change_password.visibility = View.GONE
                        resource.data.let { data -> retrieveList(data) }
                    }
                    Status.ERROR -> {
                        loading_change_password.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        loading_change_password.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(data: ResponseJSON?) {
        if (data?.status == getString(R.string.success_).toInt()) {
            Utils().toast(requireContext(), data.message.toString())
            findNavController().navigateUp()
        } else
            Utils().toast(requireContext(), data?.message.toString())
    }

}
