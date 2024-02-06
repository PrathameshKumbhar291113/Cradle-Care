package com.cradlecare.auth_module.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cradlecare.R
import com.cradlecare.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = getColor(R.color.rose_fam_400)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}