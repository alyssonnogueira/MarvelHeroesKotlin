package com.example.alysson.marvelcomicskotiln

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.example.alysson.marvelcomicskotiln.di.di.components.HeroComponent
import com.example.alysson.marvelcomicskotiln.modules.ApplicationModule
import com.example.alysson.marvelcomicskotiln.repositories.HeroRepository
import com.example.alysson.marvelcomicskotiln.viewModels.HeroPageViewModel
import io.realm.Realm
import javax.inject.Inject


class MarvelComicsApplication : MultiDexApplication() {

    lateinit var heroComponent: HeroComponent

    @Inject
    lateinit var heroPageViewModel: HeroPageViewModel

    fun get(context: Context): MarvelComicsApplication {
        return applicationContext as MarvelComicsApplication
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        heroComponent = DaggerHeroComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()

        heroComponent.inject(this)
    }

    public fun getComponent(): HeroComponent = heroComponent

}
