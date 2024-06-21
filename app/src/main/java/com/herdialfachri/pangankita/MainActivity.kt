package com.herdialfachri.pangankita

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.herdialfachri.pangankita.databinding.ActivityMainBinding
import com.herdialfachri.pangankita.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Periksa status login pengguna
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (!isLoggedIn) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //Untuk fragment yang hanya membutuhkan bottom nav
        val btmdst = listOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
        navController.addOnDestinationChangedListener{_,dst,_ ->
            if (btmdst.contains(dst.id)) {
                binding.navView.visibility = View.VISIBLE
            }
            else {
                binding.navView.visibility = View.GONE
            }
        }

        navView.setupWithNavController(navController)

        // Check if we need to navigate to NotificationsFragment
        if (intent.getBooleanExtra("navigateToNotifications", false)) {
            navController.navigate(R.id.navigation_notifications)
        }
    }
}
