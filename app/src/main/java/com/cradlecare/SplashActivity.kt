package com.cradlecare

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.cradlecare.databinding.ActivitySplashBinding
import com.cradlecare.login_module.AuthActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import splitties.activities.start

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
    }

    fun setupUi(){
        lifecycleScope.launch {
            delay(5000)
            start<AuthActivity>()
            finish()
        }
    }
}