package com.codedirect.audiometer.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.codedirect.audiometer.R
import com.codedirect.audiometer.data.source.remote.response.ResponseJSON
import com.codedirect.audiometer.data.source.remote.response.Users
import com.codedirect.audiometer.utils.SessionManager
import com.codedirect.audiometer.utils.Utils
import com.codedirect.audiometer.utils.common.Status
import com.codedirect.audiometer.utils.findNavController
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private val model by viewModel<LoginViewModel>()
    private val sessionManager by lazy {
        SessionManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkLogin()
        setupClickListener()
    }

    private fun checkLogin() {
        if (sessionManager.getLogin() == true)
            navigateToPatientDashboard()
    }

    private fun setupClickListener() {
        btn_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> login()
        }
    }

    private fun login() {
        model.getUsers(
            Users(
                username = ed_login_username.text.toString(),
                password = ed_login_password.text.toString()
            )
        ).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        login_loading.visibility = View.GONE
                        resource.data.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        login_loading.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        login_loading.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun retrieveList(
        it: ResponseJSON?
    ) {
        if (it?.status == getString(R.string.success_).toInt())
            if (it.user?.role.equals(getString(R.string.id_user_type_patient))) {
                val user = it.user
                sessionManager.setLogin(true)
                sessionManager.setIDUser(user?.id.toString())
                sessionManager.setRoleUser(getString(R.string.id_user_type_patient))
                navigateToPatientDashboard()
            } else
                navigateToCompanionDashboard()
        else
            Utils().toast(requireContext(), it?.message.toString())
    }

    private fun navigateToCompanionDashboard() {}

    private fun navigateToPatientDashboard() {
        val actions = LoginFragmentDirections.actionLoginFragmentToNavigationPatient()
        findNavController().navigate(actions)
    }
}
