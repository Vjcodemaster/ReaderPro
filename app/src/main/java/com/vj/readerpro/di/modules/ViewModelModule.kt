package com.vj.readerpro.di.modules

import androidx.lifecycle.ViewModel
import com.vj.readerpro.di.ViewModelKey
import com.vj.readerpro.ui.viewmodels.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun homeViewModel(mViewModel: HomeViewModel): ViewModel
}