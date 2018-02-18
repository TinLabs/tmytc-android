package com.tinlabs.tmytc

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.toolbar_layout.*

class PaymentListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_list)
        this.initializeComponents()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @Suppress("UNUSED_PARAMETER")
    fun addCardOnClick(v: View) {
        this.startActivity(this, RegisterCardActivity::class.java, false, null)
    }

    private fun initializeComponents() {
        // Setup toolbar
        this.toolbar.title = getString(R.string.activity_payment_list_label)
        this.setSupportActionBar(this.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
