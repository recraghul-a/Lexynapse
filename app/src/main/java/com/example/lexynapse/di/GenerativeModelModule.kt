package com.example.lexynapse.di

import com.google.firebase.Firebase
import com.google.firebase.vertexai.GenerativeModel
import com.google.firebase.vertexai.vertexAI
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
        return Firebase.vertexAI.generativeModel("gemini-2.0-flash")
    }
}