package com.example.alysson.marvelcomicskotiln

import android.support.multidex.MultiDexApplication
import com.example.alysson.marvelcomicskotiln.contracts.ApplicationComponent
import com.example.alysson.marvelcomicskotiln.modules.ApplicationModule
import io.realm.Realm

class MarvelComicsApplication : MultiDexApplication() {

    lateinit var mApplicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

}
