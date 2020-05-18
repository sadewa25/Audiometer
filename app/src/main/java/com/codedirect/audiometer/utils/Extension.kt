package com.codedirect.audiometer.utils

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT){
    Toast.makeText(requireContext(), message, duration).show()
}

fun Fragment.showToolbar(message: String) {
    (this.activity as AppCompatActivity).supportActionBar?.show()
    (this.activity as AppCompatActivity).supportActionBar?.title = message
}

fun Fragment.hideToolbar() {
    (this.activity as AppCompatActivity).supportActionBar?.hide()
}