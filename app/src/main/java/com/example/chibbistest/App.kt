package com.example.chibbistest

import android.app.Application
import com.example.chibbistest.di.DaggerViewModelFactoryComponent
import com.example.chibbistest.di.ViewModelFactoryComponent

class App: Application() {
    companion object {
        lateinit var instance: App
        val viewModelFactoryComponent: ViewModelFactoryComponent = DaggerViewModelFactoryComponent.create()
    }

    init {
        instance = this
    }
}