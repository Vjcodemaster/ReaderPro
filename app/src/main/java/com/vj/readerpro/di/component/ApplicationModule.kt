package com.vj.readerpro.di.component

import android.app.Application
import android.content.Context
import com.vj.readerpro.data.DataManager
import com.vj.readerpro.data.DataManagerImpl
import com.vj.readerpro.di.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(application: Application) {
    private val mApplication: Application = application

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return mApplication
    }

    @Provides
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    fun provideDataManager(appDataManager: DataManagerImpl): DataManager {
        return appDataManager
    }

}