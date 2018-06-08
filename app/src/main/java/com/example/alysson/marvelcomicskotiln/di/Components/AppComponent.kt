package com.example.alysson.marvelcomicskotiln.di.Components

import com.example.alysson.marvelcomicskotiln.App
import com.example.alysson.marvelcomicskotiln.di.modules.AppModule
import com.example.alysson.marvelcomicskotiln.di.modules.MainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: App)

    fun plus(mainModule: MainModule): MainComponent
}