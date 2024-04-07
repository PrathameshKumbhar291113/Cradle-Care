package com.cradlecare.pre_delivery_module.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.cradlecare.R
import com.cradlecare.databinding.ActivityPreDeliveryBinding
import com.resq360.utils.NavigationConstants.IS_PAYMENT_SUCCESSFUL
import com.resq360.utils.NavigationConstants.PAYMENT_TRANSACTION_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreDeliveryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreDeliveryBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreDeliveryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUi()
    }

    private fun setupUi() {
        window.statusBarColor = resources.getColor(R.color.rose_fam_400)

        val intentIsPaymentTransactionSuccessful = intent.getBooleanExtra(IS_PAYMENT_SUCCESSFUL, false)
        val intentPaymentTransactionId = intent.getStringExtra(PAYMENT_TRANSACTION_ID)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        if (intentIsPaymentTransactionSuccessful){
            navController.navigate(R.id.subscriptionSuccessFragment)
        }
    }
}