package com.codedirect.audiometer.ui.login

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
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
        if (sessionManager.getLogin() == true) {
            if (sessionManager.getRoleUser().equals(getString(R.string.id_user_type_patient)))
                navigateToDashboardPatient()
            else
                navigateToDashboardCompanion()
        }
    }

    private fun setupClickListener() {
        btn_login.setOnClickListener(this)
        tv_login_privacy_policy.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> login()
            R.id.tv_login_privacy_policy -> privacyPolicy()
        }
    }

    private fun privacyPolicy() {
        val urlString = "http://codedirect.id/privacy_policy_covid.html"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setData(Uri.parse(urlString))
        try {
            startActivity(intent)
        } catch (ex: ActivityNotFoundException) {
            // Chrome browser presumably not installed so allow user to choose instead
            startActivity(intent)
        }
    }

    private fun login() {
        if (ed_login_password.text.isNullOrEmpty() || ed_login_username.text.isNullOrEmpty())
            Utils().toast(requireContext(), getString(R.string.there_are_empty_data))
        else
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
        if (it?.status == getString(R.string.success_).toInt()) {
            val user = it.user
            if (user?.role.equals(getString(R.string.id_user_type_patient)))
                setupPatient(user)
            else
                setupCompanion(user)
            ed_login_password.setText("")
            ed_login_username.setText("")
        } else
            Utils().toast(requireContext(), it?.message.toString())
    }

    private fun setupCompanion(user: Users?) {
        sessionManager.setLogin(true)
        sessionManager.setIDUser(user?.id.toString())
        sessionManager.setRoleUser(getString(R.string.id_user_type_companion))
        sessionManager.setUsername(user?.username.toString())
        navigateToDashboardCompanion()
    }

    private fun navigateToDashboardCompanion() {
        val actions = LoginFragmentDirections.actionLoginFragmentToNavigationCompanion()
        findNavController().navigate(actions)
    }

    private fun setupPatient(user: Users?) {
        sessionManager.setLogin(true)
        sessionManager.setIDUser(user?.id.toString())
        sessionManager.setRoleUser(getString(R.string.id_user_type_patient))
        sessionManager.setUsername(user?.username.toString())
        navigateToDashboardPatient()
    }

    private fun navigateToDashboardPatient() {
        val actions = LoginFragmentDirections.actionLoginFragmentToNavigationPatient()
        findNavController().navigate(actions)
    }
}

