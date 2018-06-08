package com.example.alysson.marvelcomicskotiln

import android.app.Application
import com.example.alysson.marvelcomicskotiln.di.Components.AppComponent
import com.example.alysson.marvelcomicskotiln.di.Components.DaggerAppComponent
import com.example.alysson.marvelcomicskotiln.di.modules.AppModule
import io.realm.Realm

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        component.inject(this)
    }

}
