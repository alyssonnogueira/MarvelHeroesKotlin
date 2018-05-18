package com.example.alysson.marvelcomicskotiln

import android.support.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlin.Suppress

@Suppress("unused")
class MarvelComicsApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
    }

}
