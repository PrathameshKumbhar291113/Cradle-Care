package com.cradlecare.onboarding_module

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cradlecare.R
import com.cradlecare.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}