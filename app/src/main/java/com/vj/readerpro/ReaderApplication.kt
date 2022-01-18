package com.vj.readerpro

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.vj.readerpro.data.DataManager
import com.vj.readerpro.di.component.ApplicationComponent
import com.vj.readerpro.di.component.ApplicationModule
import com.vj.readerpro.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject

class ReaderApplication : Application() {

    private lateinit var mApplicationComponent: ApplicationComponent

    private lateinit var mInstance: ReaderApplication

    @Inject
    lateinit var mDataManager: DataManager

    //    protected static CacheDataSourceFactory buildReadOnlyCacheDataSource(
    //            DataSource.Factory upstreamFactory, Cache cache) {
    //        return new CacheDataSourceFactory(
    //                cache,
    //                upstreamFactory,
    //                new FileDataSource.Factory(),
    //                /* cacheWriteDataSinkFactory= */ null,
    //                CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR,
    //                /* eventListener= */ null);
    //    }
    @Synchronized
    fun getInstance(): ReaderApplication {
        return mInstance
    }

    @Override
    override fun onCreate() {
        super.onCreate()
        mInstance = this

        // Dagger DI
        mApplicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
        mApplicationComponent.inject(this)
    }

    fun getComponent(): ApplicationComponent {
        return mApplicationComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //        super.attachBaseContext(LocaleHelper.Companion.getLocalizedContext(base,(new LocaleStorage(base)).getPreferredLocale()));
        MultiDex.install(this)
    }
}