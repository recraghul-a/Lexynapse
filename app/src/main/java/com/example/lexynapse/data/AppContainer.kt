package com.example.lexynapse.data

import android.content.Context

interface AppContainer {
    val lexiRepository: LexiRepository
}

class DefaultAppContainer(private val context: Context): AppContainer {
    override val lexiRepository: LexiRepository by lazy {
        OfflineLexiRepository(LexiDatabase.getDatabase(context).lexiDao())
    }
}