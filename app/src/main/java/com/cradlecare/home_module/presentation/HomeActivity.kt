package com.cradlecare.home_module.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.cradlecare.R
import com.cradlecare.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment

    private fun setupUi() {
        window.statusBarColor = getColor(R.color.rose_fam_400)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupUi()

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
//        setupActionBarWithNavController(navController)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.mumsMateFragment -> {
                    navController.navigate(R.id.mumsMateFragment)
                    true
                }

                R.id.sosFragment ->{
                    navController.navigate(R.id.sosFragment)
                    true
                }

                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }

                else -> {
                    false
                }


            }
        }

        binding.bottomNavigationView.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                }

                R.id.mumsMateFragment -> {
                    navController.navigate(R.id.mumsMateFragment)
                }

                R.id.sosFragment -> {
                    navController.navigate(R.id.sosFragment)
                }

                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                }

                else -> {
                }
            }
        }

    }
}