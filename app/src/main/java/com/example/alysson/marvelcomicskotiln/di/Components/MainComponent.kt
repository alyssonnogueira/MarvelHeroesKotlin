package com.example.alysson.marvelcomicskotiln.di.Components

import com.example.alysson.marvelcomicskotiln.di.modules.MainModule
import com.example.alysson.marvelcomicskotiln.di.scopes.FragmentScope
import com.example.alysson.marvelcomicskotiln.views.MainActivity
import dagger.Subcomponent


@FragmentScope
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}