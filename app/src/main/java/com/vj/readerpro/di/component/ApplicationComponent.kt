package com.vj.readerpro.di.component

import android.app.Application
import android.content.Context
import android.os.Environment
import com.vj.readerpro.ReaderApplication
import com.vj.readerpro.data.DataManager
import com.vj.readerpro.data.DataManagerImpl
import com.vj.readerpro.di.ApplicationContext
import dagger.Component
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(readerApplication: ReaderApplication)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun getDataManager(): DataManager


    //fun getEnv(): Environment
}