package com.tinlabs.tmytc

import android.os.Bundle
import com.craftman.cardform.Card
import com.craftman.cardform.OnPayBtnClickListner
import kotlinx.android.synthetic.main.activity_register_card.*
import kotlinx.android.synthetic.main.toolbar_layout.*


class RegisterCardActivity : BaseActivity(), OnPayBtnClickListner {

    override fun onClick(card: Card?) {
        // TODO: Save card to payment methods
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_card)
        this.initializeComponents()
    }

    private fun initializeComponents() {
        // Setup toolbar
        this.toolbar.title = getString(R.string.activity_register_card_label)
        this.setSupportActionBar(this.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // Set listener for card form
        cardForm.setPayBtnClickListner(this)
    }
}
