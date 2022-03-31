package com.example.traincompose.di

import com.example.traincompose.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}