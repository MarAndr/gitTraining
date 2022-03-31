package com.example.traincompose

import android.app.Application
import com.example.traincompose.di.AppComponent
import com.example.traincompose.di.DaggerAppComponent

class App: Application() {

    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}