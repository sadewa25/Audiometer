package com.codedirect.elbicare.utils

import android.util.Patterns
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.afollestad.materialdialogs.MaterialDialog
import com.codedirect.elbicare.R
import com.google.android.material.textfield.TextInputLayout

fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), message, duration).show()
}

fun <T : ViewDataBinding?> Fragment.setFragBinding(layoutId: Int, container: ViewGroup?): T {
    return DataBindingUtil.inflate<T>(layoutInflater, layoutId, container, false)
}

fun Fragment.showToolbar(message: String) {
    (this.activity as AppCompatActivity).supportActionBar?.show()
    (this.activity as AppCompatActivity).supportActionBar?.title = message
}

fun Fragment.hideToolbar() {
    (this.activity as AppCompatActivity).supportActionBar?.hide()
}

fun AppCompatActivity.hideToolbar() {
    this.supportActionBar?.hide()
}

fun Fragment.nameValidation(name: String, ti: TextInputLayout): Boolean {
    var data = false
    if (name.isNullOrEmpty())
        ti.error = getString(R.string.cannot_empty)
    else {
        ti.error = ""
        data = true
    }
    return data
}

fun Fragment.phoneValidation(phone: String, ti: TextInputLayout): Boolean {
    var data = false
    if (phone.isNullOrEmpty())
        ti.error = getString(R.string.cannot_empty)
    else {
        data = true
        ti.error = ""
    }
    return data
}

fun Fragment.emailValidation(email: String, ti: TextInputLayout): Boolean {
    var data = false
    if (email.isNullOrEmpty())
        ti.error = getString(R.string.cannot_empty)
    else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        ti.error = getString(R.string.wrong_email_format)
    else {
        data = true
        ti.error = ""
    }
    return data
}

fun Fragment.passwordValidation(password: String, ti: TextInputLayout): Boolean {
    var data = false
    if (password.isNullOrEmpty())
        ti.error = getString(R.string.cannot_empty)
    else {
        ti.error = ""
        data = true
    }
    return data
}

fun Fragment.isPasswordAndConfirmPasswordSame(
    password: String,
    confirmPassword: String,
    ti: TextInputLayout
): Boolean {
    var data = false
    if (password != confirmPassword)
        ti.error = getString(R.string.password_not_match)
    else {
        ti.error = ""
        data = true
    }
    return data
}

fun Fragment.confirmationMaterialDialog(
    title: String,
    message: String,
    listenerPositive: (MaterialDialog) -> Unit
) {
    MaterialDialog(requireContext()).show {
        title(text = title)
        message(text = message)
        positiveButton(text = context.resources?.getString(R.string.yes_)) {
            listenerPositive(it)
        }
        negativeButton(text = context.resources?.getString(R.string.no_)) {
            it.dismiss()
        }
    }
}

fun Fragment.successDialog(
    title: String,
    content: String,
    listenerPositive: (SweetAlertDialog) -> Unit
) {
    SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE).setTitleText(title)
        .setContentText(content)
        .setConfirmText(getString(R.string.ok_))
        .setConfirmClickListener {
            listenerPositive(it)
        }
        .showCancelButton(false)
        .show()
}

fun Fragment.errorDialog(
    title: String,
    content: String
) {
    SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
        .setTitleText(title)
        .setContentText(content)
        .show()
}