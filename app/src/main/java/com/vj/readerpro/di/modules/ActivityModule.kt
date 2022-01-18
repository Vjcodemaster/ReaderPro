package com.vj.readerpro.di.modules

import android.app.Activity
import android.content.Context
import com.vj.readerpro.di.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(activity: Activity) {
    private val mActivity: Activity

    @Provides
    @ActivityContext
    fun provideContext(): Context {
        return mActivity
    }

    @Provides
    fun provideActivity(): Activity {
        return mActivity
    }

    init {
        mActivity = activity
    }
}