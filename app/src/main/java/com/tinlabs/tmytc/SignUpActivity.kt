package com.tinlabs.tmytc

import android.os.Bundle
import android.view.View

class SignUpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        this.initializeComponents()
    }

    private fun initializeComponents() {

    }

    @Suppress("UNUSED_PARAMETER")
    fun loginOnClick(v: View) {
        this.onBackPressed()
    }
}
