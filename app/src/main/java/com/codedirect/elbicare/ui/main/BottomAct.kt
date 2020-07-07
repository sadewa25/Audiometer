package com.codedirect.elbicare.ui.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.codedirect.elbicare.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_bottom.*

class BottomAct : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.reportPatientFragment, R.id.historyFragment, R.id.profileFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = FirebaseAnalytics.getInstance(applicationContext)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.reportPatientFragment -> showBottomNav()
                R.id.historyFragment -> showBottomNav()
                R.id.profileFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun showBottomNav() {
        nav_view.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        nav_view.visibility = View.GONE
    }

}