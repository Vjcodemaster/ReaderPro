package com.vj.readerpro.base.activity

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.vj.readerpro.ReaderApplication
import com.vj.readerpro.base.ViewModelFactory
import com.vj.readerpro.data.DataManager
import com.vj.readerpro.di.component.ActivityComponent
import com.vj.readerpro.di.component.DaggerActivityComponent
import com.vj.readerpro.di.modules.ActivityModule
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {


    lateinit var mActivityComponent: ActivityComponent

    @Inject
    lateinit var dataManager: DataManager

    @Inject
    lateinit var vmFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        mActivityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .applicationComponent((application as ReaderApplication).getComponent())
            .build()

        mActivityComponent.inject(this)
    }

    open fun getActivityComponent(): ActivityComponent? {
        return mActivityComponent
    }

    open fun getAppContext(): Context? {
        return applicationContext
    }

    open fun getApplicationInstance(): Application? {
        return application
    }
}