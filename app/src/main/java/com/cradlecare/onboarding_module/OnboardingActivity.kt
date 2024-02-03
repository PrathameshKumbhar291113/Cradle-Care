package com.cradlecare.onboarding_module

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cradlecare.R
import com.cradlecare.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.rose_fam_400)
    }
}