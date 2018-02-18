package com.tinlabs.tmytc

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.tinlabs.tmytc.enum.BundleConstants
import com.tinlabs.tmytc.preference.AppSharedPreferences
import kotlinx.android.synthetic.main.toolbar_layout.*


class MainActivity : BaseActivity() {

    private var isUserLogged: Boolean = false

    private var drawer: Drawer? = null
    private var homeItem: PrimaryDrawerItem? = null
    private var helpItem: PrimaryDrawerItem? = null
    private var loginItem: PrimaryDrawerItem? = null
    private var logoutItem: PrimaryDrawerItem? = null
    private var paymentItem: PrimaryDrawerItem? = null

    private val drawerListener = Drawer.OnDrawerItemClickListener { _, _, drawerItem ->
        when (drawerItem) {
            loginItem -> {
                this.startActivity(this@MainActivity, LoginActivity::class.java,
                        false, null)
                finish()
            }
            logoutItem -> {
                AppSharedPreferences.getInstance(this@MainActivity).clearPreference()
                this.startActivity(this@MainActivity, MainActivity::class.java, false,
                        null)
                finish()
            }
            paymentItem -> {
                this.startActivity(this@MainActivity, PaymentListActivity::class.java, false,
                        null)
            }
            else -> {

            }
        }
        false
    }

    override fun onResume() {
        super.onResume()
        if (this.drawer != null) {
            this.drawer!!.setSelection(this.homeItem!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.initializeComponents()
    }

    private fun initializeComponents() {
        // Get data from bundle
        val mBundle = intent.extras
        if (mBundle != null) {
            // Validate if any user is logged
            this.isUserLogged = mBundle.getBoolean(BundleConstants.IS_USER_LOGGED, false)
        }
        // Setup toolbar
        this.toolbar.title = getString(R.string.app_name)
        this.setSupportActionBar(this.toolbar)
        // Build Navigation Drawer for current user
        this.buildNavigationDrawer()
    }

    private fun buildNavigationDrawer() {
        // Get profile for logged user
        val profile = this.buildProfileHeader()
        // Build header profile
        val headerResult = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile
                )
                .withSelectionListEnabled(false)
                .build()
        // Create all items instance
        this.buildAllItems()
        // Create a builder
        val drawerBuilder = DrawerBuilder().withActivity(this)
                .withToolbar(this.toolbar)
                .addDrawerItems(
                        homeItem,
                        DividerDrawerItem()
                )
                .withOnDrawerItemClickListener(this.drawerListener)
                .withAccountHeader(headerResult)
        // Build navigation items depend of user
        if (this.isUserLogged) {
            this.buildRegisteredDrawerItems(drawerBuilder)
        } else {
            this.buildUnRegisteredDrawerItems(drawerBuilder)
        }
        // Build Navigation Drawer
        this.drawer = drawerBuilder.build()
        // Setup toolbar navigation icons
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        this.drawer!!.actionBarDrawerToggle.isDrawerIndicatorEnabled = true
    }

    private fun buildAllItems() {
        this.homeItem = PrimaryDrawerItem().withName(getString(R.string.drawer_item_home))
                .withIcon(R.drawable.ic_home_grey_24dp)
        this.helpItem = PrimaryDrawerItem().withName(getString(R.string.drawer_item_help))
                .withIcon(R.drawable.ic_help_outline_grey_24dp)
        this.logoutItem = PrimaryDrawerItem().withName(getString(R.string.drawer_item_logout))
                .withIcon(R.drawable.ic_keyboard_return_grey_24dp)
        this.loginItem = PrimaryDrawerItem().withName(getString(R.string.drawer_item_login))
                .withIcon(R.drawable.ic_exit_to_app_grey_24dp)
        this.paymentItem = PrimaryDrawerItem().withName(getString(R.string.drawer_item_payment))
                .withIcon(R.drawable.ic_credit_card_grey_24dp)
    }

    private fun buildRegisteredDrawerItems(drawerBuilder: DrawerBuilder) {
        // Add items to builder
        drawerBuilder.addDrawerItems(
                this.paymentItem,
                this.helpItem,
                DividerDrawerItem(),
                this.logoutItem)
    }

    private fun buildUnRegisteredDrawerItems(drawerBuilder: DrawerBuilder) {
        // Add items to builder
        drawerBuilder.addDrawerItems(
                this.helpItem,
                DividerDrawerItem(),
                this.loginItem
        )

    }

    private fun buildProfileHeader(): ProfileDrawerItem {
        return if (this.isUserLogged) {
            val user = AppSharedPreferences.getInstance(this).getUser()
            ProfileDrawerItem()
                    .withName(user.name)
                    .withEmail(user.email)
                    .withIcon(ContextCompat.getDrawable(this, R.drawable.profile))
        } else {
            ProfileDrawerItem()
                    .withName(getString(R.string.welcome_label))
                    .withEmail(getString(R.string.welcome_second_label))
                    .withIcon(ContextCompat.getDrawable(this, R.drawable.profile_default))
        }
    }
}
