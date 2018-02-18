package com.tinlabs.tmytc

import android.os.Bundle
import android.os.Handler
import com.tinlabs.tmytc.enum.BundleConstants
import com.tinlabs.tmytc.preference.AppSharedPreferences


class SplashActivity : BaseActivity() {

    companion object {
        val SPLASH_TIME_OUT: Long = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        this.initializeComponents()
    }

    private fun initializeComponents() {
        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            val preference = AppSharedPreferences.getInstance(this@SplashActivity)
            // Get current user
            val user = preference.getUser()
            // Validate if exist one
            val isLogged = !user.name.isEmpty()
            val mBundle = Bundle()
            // Put flag on bundle
            mBundle.putBoolean(BundleConstants.IS_USER_LOGGED, isLogged)
            // Start a MainActivity
            startActivity(this@SplashActivity, MainActivity::class.java, true, mBundle)
            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}
