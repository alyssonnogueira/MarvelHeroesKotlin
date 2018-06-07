package com.example.alysson.marvelcomicskotiln.contracts

import com.example.alysson.marvelcomicskotiln.modules.ActivityModule
import com.example.alysson.marvelcomicskotiln.modules.ApplicationModule
import com.example.alysson.marvelcomicskotiln.views.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        dependencies = [
            ApplicationComponent::class
        ],
        modules = [ActivityModule::class])
interface HeroComponent{
    fun inject(mainActivity: MainActivity)
}