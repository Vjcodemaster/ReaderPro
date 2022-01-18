package com.vj.readerpro.data

import android.content.Context
import com.vj.readerpro.di.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManagerImpl
@Inject constructor(@param:ApplicationContext private val mContext: Context)
    : DataManager() {

    /*fun deleteAllData() {
        deleteAllData()
    }*/
}