package com.example.alysson.marvelcomicskotiln.di.di.components

import com.example.alysson.marvelcomicskotiln.PerActivity
import com.example.alysson.marvelcomicskotiln.modules.ActivityModule
import com.example.alysson.marvelcomicskotiln.views.HeroPageActivity
import com.example.alysson.marvelcomicskotiln.views.MainActivity
import com.example.alysson.marvelcomicskotlin.views.MainActivity
import dagger.Component


@PerActivity
@Component(dependencies = [HeroComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(heroPageActivity: HeroPageActivity)

}