package com.vj.readerpro.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vj.readerpro.data.DataManager
import javax.inject.Inject

class HomeViewModel@Inject constructor(
    application: Application, val dataManager: DataManager /*just incase if we want to store any data universal to app, like sharedPreference data*/
) : AndroidViewModel(application) {

}