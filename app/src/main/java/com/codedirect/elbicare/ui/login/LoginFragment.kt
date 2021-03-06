package com.codedirect.elbicare.ui.login

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.codedirect.elbicare.R
import com.codedirect.elbicare.data.source.remote.response.ResponseJSON
import com.codedirect.elbicare.data.source.remote.response.Users
import com.codedirect.elbicare.utils.*
import com.codedirect.elbicare.utils.common.Status
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
        hideToolbar()
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
        if (checkEmpty())
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

    private fun checkEmpty(): Boolean {
        val a = arrayListOf(
            nameValidation(ed_login_username.text.toString(), ti_login_email),
            nameValidation(ed_login_password.text.toString(), ti_login_password)
        )
        return a.all { it == true }
    }

    private fun retrieveList(
        it: ResponseJSON?
    ) {
        if (it?.status == getString(R.string.success_).toInt()) {
            val user = it.user
            ed_login_password.setText("")
            ed_login_username.setText("")
            successDialog(getString(R.string.information), it.message.toString()) {
                if (user?.role.equals(getString(R.string.id_user_type_patient)))
                    setupPatient(user)
                else
                    setupCompanion(user)
                it.dismissWithAnimation()
            }
        } else
            errorDialog(getString(R.string.information), it?.message.toString())
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
        /*val actions = LoginFragmentDirections.actionLoginFragmentToNavigationPatient()
        findNavController().navigate(actions)*/
        val actions = LoginFragmentDirections.actionLoginFragmentToBottomAct()
        findNavController().navigate(actions)
        requireActivity().finish()
    }
}

