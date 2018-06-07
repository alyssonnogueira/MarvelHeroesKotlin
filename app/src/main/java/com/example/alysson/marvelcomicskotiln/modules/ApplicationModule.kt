package com.example.alysson.marvelcomicskotiln.modules

import android.content.SharedPreferences
import dagger.Provides
import android.app.Application
import android.content.Context
import com.example.alysson.marvelcomicskotiln.ApplicationContext
import com.example.alysson.marvelcomicskotiln.DatabaseInfo
import dagger.Module
import javax.inject.Singleton


@Module
class ApplicationModule(private val mApplication: Application) {
    private val PREF_KEY: String = "pk"

    @Provides
    @Singleton
    fun providePreferences(): SharedPreferences{
        return (this.mApplication.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE))
    }

//    @Provides
//    @ApplicationContext
//    internal fun provideContext(): Context {
//        return mApplication
//    }
//
//    @Provides
//    internal fun provideApplication(): Application {
//        return mApplication
//    }
//
//    @Provides
//    @DatabaseInfo
//    internal fun provideDatabaseName(): String {
//        return "demo-dagger.db"
//    }
//
//    @Provides
//    @DatabaseInfo
//    internal fun provideDatabaseVersion(): Int? {
//        return 2
//    }
//
//    @Provides
//    internal fun provideSharedPrefs(): SharedPreferences {
//        return mApplication.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE)
//    }
}