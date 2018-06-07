package com.example.alysson.marvelcomicskotiln.contracts

import com.example.alysson.marvelcomicskotiln.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [ApplicationModule::class])
interface ApplicationComponent