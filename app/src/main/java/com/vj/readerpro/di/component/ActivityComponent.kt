package com.vj.readerpro.di.component

import com.vj.readerpro.base.activity.BaseActivity
import com.vj.readerpro.di.PerActivity
import com.vj.readerpro.di.modules.ActivityModule
import com.vj.readerpro.di.modules.ViewModelModule
import com.vj.readerpro.ui.activities.HomeActivity
import dagger.Component
import javax.inject.Singleton

@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class, ViewModelModule::class]
)
interface ActivityComponent {

    fun inject(baseActivity: BaseActivity)

    fun inject(homeActivity: HomeActivity)
}