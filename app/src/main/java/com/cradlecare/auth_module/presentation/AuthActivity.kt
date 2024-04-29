package com.cradlecare.auth_module.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cradlecare.databinding.ActivityAuthBinding
import com.cradlecare.utils.appStatusBarColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appStatusBarColor(this, window)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}