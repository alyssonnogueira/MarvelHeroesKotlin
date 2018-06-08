package com.example.alysson.marvelcomicskotiln.di.modules

import com.example.alysson.marvelcomicskotiln.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module class AppModule(val app: App) {
    @Provides @Singleton fun provideApp() = app
}