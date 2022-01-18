package com.vj.readerpro.ui.viewmodels

import android.app.Application
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.vj.readerpro.data.DataManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel@Inject constructor(
    application: Application, val dataManager: DataManager /*just incase if we want to store any data universal to app, like sharedPreference data*/
) : AndroidViewModel(application) {


    fun fetchSMSData(contentResolver : ContentResolver){
        CoroutineScope(Dispatchers.Default).launch {
            // Create Inbox box URI
            val inboxURI: Uri = Uri.parse("content://sms/inbox")

            // List required columns
            val reqCols = arrayOf("_id", "address", "body")

            // Get Content Resolver object, which will deal with Content Provider
            val cr = contentResolver

            // Fetch Inbox SMS Message from Built-in Content Provider
            val cursor: Cursor? = cr.query(inboxURI, reqCols, null, null, null)

            val alSMSList : ArrayList<String> = arrayListOf()

            while(cursor?.moveToNext() == true) {
                alSMSList.add(cursor.getString(cursor.getColumnIndex("address")))
            }
        }
    }
}