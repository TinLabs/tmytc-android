package com.tinlabs.tmytc.preference

import android.content.Context
import android.content.SharedPreferences
import com.tinlabs.tmytc.preference.AppSharedPreferences.Preferences.PREFERENCE_NAME

/**
 * Created by Javier Lage on 16/02/18.
 */
class AppSharedPreferences(context: Context) {

    object Preferences {
        val PREFERENCE_NAME = "tmytc_shared_preferences"
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
}