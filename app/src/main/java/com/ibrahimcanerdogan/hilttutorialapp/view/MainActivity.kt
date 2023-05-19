package com.ibrahimcanerdogan.hilttutorialapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ibrahimcanerdogan.hilttutorialapp.R
import com.ibrahimcanerdogan.hilttutorialapp.databinding.ActivityMainBinding
import com.ibrahimcanerdogan.hilttutorialapp.util.event.TaskEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav : BottomNavigationView
    private lateinit var navController : NavController
    private lateinit var navHostFragment : NavHostFragment

    private lateinit var binding: ActivityMainBinding
    private val viewModel : TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        bottomNav = binding.bottomNavigationView
        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        bottomNav.setupWithNavController(navController)
        // for bottom navigation with action bar reaction.
        //NavigationUI.setupActionBarWithNavController(this, navController)
        NavigationUI.setupWithNavController(bottomNav, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

}