package com.example.lexynapse

import android.app.Application
import com.example.lexynapse.data.AppContainer
import com.example.lexynapse.data.DefaultAppContainer
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class LexiApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}