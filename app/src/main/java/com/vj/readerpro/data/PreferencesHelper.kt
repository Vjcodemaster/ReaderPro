package com.vj.readerpro.data

import com.vj.readerpro.utils.AppConstants

interface PreferencesHelper {
    // user logged in mode
    fun getCurrentUserLoggedInMode(): Int
    fun setCurrentUserLoggedInMode(mode: AppConstants.LoggedInMode)

}