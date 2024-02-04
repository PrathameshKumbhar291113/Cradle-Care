package com.cradlecare

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CradleCareApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}