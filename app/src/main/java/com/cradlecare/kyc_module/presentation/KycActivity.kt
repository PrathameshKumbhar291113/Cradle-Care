package com.cradlecare.kyc_module.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cradlecare.databinding.ActivityKycBinding
import com.cradlecare.utils.appStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KycActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKycBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityKycBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUi()
    }

    private fun setUpUi() {
        appStatusBarColor(this, window)
    }
}