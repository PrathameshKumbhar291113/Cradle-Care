package com.cradlecare

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.cradlecare.auth_module.presentation.AuthActivity
import com.cradlecare.databinding.ActivitySplashBinding
import com.cradlecare.home_module.presentation.HomeActivity
import com.cradlecare.onboarding_module.presentation.OnboardingActivity
import com.resq360.utils.BundleConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.activities.start

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
    }

    private fun setupUi() {

        window.statusBarColor = getColor(R.color.rose_fam_400)

        val sharePrefLogin: SharedPreferences =
            getSharedPreferences(BundleConstants.LOGIN, Context.MODE_PRIVATE)
        val sharePrefOnboarded: SharedPreferences =
            getSharedPreferences(BundleConstants.ONBOARDING, Context.MODE_PRIVATE)

        var isLoginCompleted = sharePrefLogin.getBoolean(BundleConstants.IS_LOGIN_COMPLETED, false)
        var isOnBoardingCompleted = sharePrefOnboarded.getBoolean(BundleConstants.IS_ONBOARDING_COMPLETED, false)

        if (!isLoginCompleted) {
            lifecycleScope.launch {
                delay(5000)
                start<AuthActivity>()
                finish()
            }
        } else if (!isOnBoardingCompleted) {
            lifecycleScope.launch {
                delay(5000)
                start<OnboardingActivity>()
                finish()
            }
        } else if (isLoginCompleted && isOnBoardingCompleted){
            //Home activity
            lifecycleScope.launch {
                delay(5000)
                start<HomeActivity>()
                finish()
            }
        }
    }
}