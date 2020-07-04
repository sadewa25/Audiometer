package com.codedirect.pulmos.utils

import android.content.Context
import android.content.SharedPreferences
import com.codedirect.pulmos.R

class SessionManager(private val context: Context?) {

    // Shared pref mode
    val PRIVATE_MODE = 0

    // Sharedpref file name
    private val PREF_NAME = context?.resources?.getString(R.string.app_name)

    var pref: SharedPreferences? = context?.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    var editor: SharedPreferences.Editor? = pref?.edit()

    private val is_login = "is_login"
    fun setLogin(check: Boolean) {
        editor?.putBoolean(is_login, check)
        editor?.apply()
    }

    fun getLogin(): Boolean? {
        return pref?.getBoolean(is_login, false);
    }

    private val idUser = "_id"
    fun setIDUser(data: String) {
        editor?.putString(idUser, data)
        editor?.apply()
    }

    fun getIDUser(): String? {
        return pref?.getString(idUser, ""); }

    private val roleUser = "roleUser"
    fun setRoleUser(data: String) {
        editor?.putString(roleUser, data)
        editor?.apply()
    }

    fun getRoleUser(): String? {
        return pref?.getString(roleUser, ""); }

    private val username = "username"
    fun setUsername(data: String) {
        editor?.putString(username, data)
        editor?.apply()
    }

    fun getUsername(): String? {
        return pref?.getString(username, ""); }


}