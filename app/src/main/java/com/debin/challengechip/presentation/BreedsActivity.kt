package com.debin.challengechip.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.debin.challengechip.R

class BreedsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breeds)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this,
            R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }
}