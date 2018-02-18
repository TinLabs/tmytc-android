package com.tinlabs.tmytc.preference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.tinlabs.tmytc.data.entity.User
import com.tinlabs.tmytc.preference.AppSharedPreferences.Preferences.PREFERENCE_NAME

/**
 * Created by Javier Lage.
 */
class AppSharedPreferences(context: Context) {

    object Preferences {
        val PREFERENCE_NAME = "tmytc_shared_preferences"
        val USER_NAME = "user_name"
        val USER_EMAIL = "user_email"
        val USER_TOKEN = "user_token"
    }

    companion object {
        private var mInstance: AppSharedPreferences? = null
        fun getInstance(context: Context): AppSharedPreferences {
            if (mInstance == null) {
                mInstance = AppSharedPreferences(context)
            }
            return mInstance as AppSharedPreferences
        }
    }

    private var sharedPreferences: SharedPreferences = context
            .getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor? = null


    fun getUser(): User {
        val user = User()
        user.name = sharedPreferences.getString(Preferences.USER_NAME, "")
        user.email = sharedPreferences.getString(Preferences.USER_EMAIL, "")
        user.token = sharedPreferences.getString(Preferences.USER_TOKEN, "")
        return user
    }

    fun setUser(user: User) {
        this.doEdit()
        this.editor!!.putString(Preferences.USER_NAME, user.name)
        this.editor!!.putString(Preferences.USER_EMAIL, user.email)
        this.editor!!.putString(Preferences.USER_TOKEN, user.token)
        this.doCommit()
    }

    fun clearPreference() {
        this.doEdit()
        this.editor!!.clear()
        this.editor!!.apply()
    }

    @SuppressLint("CommitPrefEdits")
    private fun doEdit() {
        if (this.editor == null) {
            this.editor = this.sharedPreferences.edit()
        }
    }

    private fun doCommit() {
        if (this.editor != null) {
            this.editor!!.apply()
            this.editor = null
        }
    }
}