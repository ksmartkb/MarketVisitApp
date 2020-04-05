package com.example.android.market_master.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import com.example.android.market_master.R
import com.example.android.market_master.domain.Outlet
import com.example.android.market_master.models.OutletStateEvent
import com.example.android.market_master.models.OutletViewModel
import com.example.android.market_master.ui.outlet.map.OutletMapViewFragment
import com.example.android.market_master.utils.DataState
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_outlet_map_view.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: OutletViewModel by viewModels()
    // Initialise the DrawerLayout, NavigationView and ToggleBar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObservers()
        viewModel.setOuletStateEvent(OutletStateEvent.GetOutletsEvent)

        // Call findViewById on the DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout)

        // Pass the ActionBarToggle action into the drawerListener
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
        actionBarToggle.syncState()

        // Call findViewById on the NavigationView
        navView = findViewById(R.id.navView)

        // Call setNavigationItemSelectedListener on the NavigationView to detect when items are clicked
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.my_map -> {

                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container,
                            OutletMapViewFragment(), "OutletMapViewFragment")
                        .commit()
                    Toast.makeText(this, "My Maps", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.summary -> {
                    Toast.makeText(this, "Summary", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.logout -> {
                    Toast.makeText(this, "System Logout", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
    private fun subscribeObservers(){
        viewModel.dataState.observe(this, Observer { dataState ->
            when(dataState){
                is DataState.Success<List<Outlet>> -> {
                    displayProgressBar(false)
                    // showOutlets(dataState.data)
                    onOpenMap()
                    //showMainFragment()
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }
    // override the onSupportNavigateUp() function to launch the Drawer when the hamburger icon is clicked
    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView)
        return true
    }

    // override the onBackPressed() function to close the Drawer when the back button is clicked
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun displayError(message: String?){
        if(message != null) text.text = message else text.text = "Unknown error."
    }

    private fun showOutlets(outlets: List<Outlet>){
        val sb = StringBuilder()
        for(outlet in outlets){
            sb.append(outlet.outletName + "\n")
        }
        text.text = sb.toString()
    }

    private fun displayProgressBar(isDisplayed: Boolean){
        progress_bar.visibility = if(isDisplayed) View.VISIBLE else View.GONE
    }

    private fun onOpenMap(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,
                OutletMapViewFragment(), "OutletMapViewFragment")
            .commit()

    }
}
