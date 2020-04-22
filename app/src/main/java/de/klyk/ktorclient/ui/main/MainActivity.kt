package de.klyk.ktorclient.ui.main

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import de.klyk.ktorclient.R
import de.klyk.ktorclient.databinding.MainActivityBinding
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_tabs_fragment.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), AppBarConfiguration.OnNavigateUpListener {

    val viewModel: MainActivityViewModel by inject()
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity).run {
            viewModel = this@MainActivity.viewModel
            lifecycleOwner = this@MainActivity
        }

        setupNavigation()
        setUpToolbar()
    }

    private fun setUpToolbar() {
        toolbar.title = "Ktor Android Client"
        setSupportActionBar(toolbar)
    }

    private fun setupNavigation() {
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        findViewById<Toolbar>(R.id.toolbar).setupWithNavController(
                navController,
                appBarConfiguration
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment)) || super.onOptionsItemSelected(
                item
        )
    }
}
