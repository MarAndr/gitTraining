package com.example.traincompose.di

import androidx.lifecycle.ViewModelProvider
import com.example.traincompose.MyViewModelFactory
import com.example.traincompose.Repo
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideFactory(): MyViewModelFactory{
        return MyViewModelFactory(repo = Repo())
    }


    @Provides
    fun provideRepo(): Repo{
        return Repo()
    }
}