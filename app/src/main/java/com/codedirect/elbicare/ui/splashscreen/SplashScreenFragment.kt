package com.codedirect.elbicare.ui.splashscreen


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.codedirect.elbicare.R
import com.codedirect.elbicare.utils.SessionManager
import com.codedirect.elbicare.utils.Utils
import com.codedirect.elbicare.utils.findNavController
import com.codedirect.elbicare.utils.hideToolbar
import com.novoda.merlin.*

class SplashScreenFragment : Fragment(), Connectable, Disconnectable, Bindable {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    private val merlin: Merlin by lazy {
        Merlin.Builder().withConnectableCallbacks().withDisconnectableCallbacks()
            .withBindableCallbacks().build(context)
    }

    private val sessionManager by lazy {
        SessionManager(requireContext())
    }

    override fun onResume() {
        super.onResume()
        merlin.bind()
        merlin.bind()
        merlin.registerConnectable(this)
        merlin.registerBindable(this)
        merlin.registerDisconnectable(this)
    }

    override fun onPause() {
        merlin.unbind()
        super.onPause()
    }

    private fun splashScreenRun() {
        Handler().postDelayed({
            if (sessionManager.getLogin() == true)
                navigateToBottomAct()
            else
                navigateToLogin()
        }, 1000)
    }

    private fun navigateToBottomAct() {
        val actions = SplashScreenFragmentDirections.actionSplashScreenFragmentToBottomAct()
        findNavController().navigate(actions)
        requireActivity().finish()
    }

    private fun navigateToLogin() {
        val actions = SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginFragment()
        findNavController().navigate(actions)
    }

    override fun onConnect() {}
    override fun onDisconnect() {}

    override fun onBind(networkStatus: NetworkStatus?) {
        if (networkStatus != null) {
            if (networkStatus.isAvailable) {
                splashScreenRun()
            } else {
                Utils().toast(requireContext(), getString(R.string.network_unavailable))
                activity?.finish()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideToolbar()
    }

}
