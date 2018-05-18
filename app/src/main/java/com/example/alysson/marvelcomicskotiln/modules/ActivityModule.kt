package com.example.alysson.marvelcomicskotiln.modules

import android.app.Activity
import android.content.Context
import com.example.alysson.marvelcomicskotiln.ActivityContext
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    @ActivityContext
    internal fun provideContext(): Context {
        return mActivity
    }

    @Provides
    internal fun provideActivity(): Activity {
        return mActivity
    }
}