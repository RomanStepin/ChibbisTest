package com.example.chibbistest.di

import com.example.chibbistest.ai.ViewModelFactory
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [RestApiModule::class])
interface ViewModelFactoryComponent {
    fun getViewModelFactory(): ViewModelFactory
}