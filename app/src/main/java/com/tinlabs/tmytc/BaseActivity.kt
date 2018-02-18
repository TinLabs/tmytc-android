package com.tinlabs.tmytc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Javier Lage.
 */
open class BaseActivity : AppCompatActivity() {

    /**
     * Navigate to another activity
     */
    fun startActivity(fromContext: Context, toContext: Class<*>, clearStack: Boolean, bundle: Bundle?) {
        val intent = Intent(fromContext, toContext)
        if (clearStack) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }
}