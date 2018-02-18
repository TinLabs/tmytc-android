package com.tinlabs.tmytc

import android.os.Bundle
import android.view.View
import com.tinlabs.tmytc.data.entity.User
import com.tinlabs.tmytc.enum.BundleConstants
import com.tinlabs.tmytc.preference.AppSharedPreferences
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        this.initializeComponents()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.startActivity(this@LoginActivity, MainActivity::class.java,
                false, null)
        finish()
    }

    @Suppress("UNUSED_PARAMETER")
    fun doLoginOnClick(v: View) {
        if (isValidForm()) {
            // TODO: Do login to WS
            val user = User()
            user.name = "Javier Lage"
            user.email = this.inputEmail.text.toString()
            // Save user
            AppSharedPreferences.getInstance(this).setUser(user)
            val mBundle = Bundle()
            // Put flag on bundle
            mBundle.putBoolean(BundleConstants.IS_USER_LOGGED, true)
            this.startActivity(this@LoginActivity, MainActivity::class.java,
                    false, mBundle)
            finish()
        }
    }

    @Suppress("UNUSED_PARAMETER")
    fun signUpOnClick(v: View) {
        this.startActivity(this, SignUpActivity::class.java, false, null)
    }

    private fun initializeComponents() {

    }

    private fun isValidForm(): Boolean {
        if (this.inputEmail.text.isEmpty()) {
            this.inputEmail.error = getString(R.string.form_required_field)
            return false
        }
        if (this.inputPassword.text.isEmpty()) {
            this.inputEmail.error = getString(R.string.form_required_field)
            return false
        }
        return true
    }
}
