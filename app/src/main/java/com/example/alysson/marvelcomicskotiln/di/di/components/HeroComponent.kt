package com.example.alysson.marvelcomicskotiln.di.di.components

import android.app.Application
import android.content.Context
import com.example.alysson.marvelcomicskotiln.ApplicationContext
import com.example.alysson.marvelcomicskotiln.MarvelComicsApplication
import com.example.alysson.marvelcomicskotiln.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton


@Component(modules = [ApplicationModule::class])
@Singleton
interface HeroComponent {
    fun inject(marvelComicsApplication: MarvelComicsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getApplication(): Application

//    fun getDataManager(): DataManager
//
//    fun getPreferenceHelper(): SharedPrefsHelper
//
//    fun getDbHelper(): DbHelper

}