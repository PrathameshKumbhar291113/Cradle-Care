package com.cradlecare.onboarding_module.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cradlecare.databinding.ActivityOnboardingBinding
import com.cradlecare.utils.appStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appStatusBarColor(this, window)
    }
}