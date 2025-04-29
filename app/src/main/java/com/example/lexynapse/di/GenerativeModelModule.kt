package com.example.lexynapse.di

import androidx.core.os.BuildCompat
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object GenerativeModelModule {
    @Provides
    @Singleton
    fun provideGenerativeModel(): GenerativeModel {
        val apiKey = "API_KEY_HERE_PLS_YOURS_MIND_THAT"
        return GenerativeModel(
            modelName = "gemini-2.0-flash",
            apiKey = apiKey
        )
    }
}