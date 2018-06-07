package com.example.alysson.marvelcomicskotiln.modules

import android.app.Activity
import android.content.Context
import com.example.alysson.marvelcomicskotiln.ActivityContext
import com.example.alysson.marvelcomicskotiln.models.Hero
import com.example.alysson.marvelcomicskotiln.repositories.HeroRepository
import com.example.alysson.marvelcomicskotiln.scopes.PerActivity
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    @PerActivity
    fun provideHero(): Hero = Hero()

    @Provides
    @PerActivity
    fun provideHeroRepository(context: Context): HeroRepository = HeroRepository(context)
}