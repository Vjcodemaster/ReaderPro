package com.vj.readerpro.ui.viewmodels

import android.app.Application
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.text.format.DateFormat
import android.text.format.DateUtils
import androidx.lifecycle.AndroidViewModel
import com.vj.readerpro.data.DataManager
import com.vj.readerpro.data.model.SMSData
import com.vj.readerpro.utils.AppConstants
import com.vj.readerpro.utils.EnumConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList




class HomeViewModel@Inject constructor(
    application: Application, val dataManager: DataManager /*just incase if we want to store any data universal to app, like sharedPreference data*/
) : AndroidViewModel(application) {


    fun fetchSMSData(contentResolver : ContentResolver){
        CoroutineScope(Dispatchers.Default).launch {
            // Create Inbox box URI
            val inboxURI: Uri = Uri.parse("content://sms/inbox")

            // List required columns
            val reqCols = arrayOf("_id", "address", "date", "body", "person")

            // Get Content Resolver object, which will deal with Content Provider
            val cr = contentResolver

            // Fetch Inbox SMS Message from Built-in Content Provider
            val cursor: Cursor? = cr.query(inboxURI, reqCols, null, null, null)

            //val alSMSList : ArrayList<String> = arrayListOf()
            val alSMSList : ArrayList<SMSData> = arrayListOf()

            while(cursor?.moveToNext() == true) {
                //alSMSList.add(cursor.getString(cursor.getColumnIndex("address")))
                //alSMSList.add(cursor.getString(cursor.getColumnIndex("_id")))
                    val smsTimeStamp : String  = cursor.getString(cursor.getColumnIndex(AppConstants.SMS_DB_KEY_DATE))
                    val smsData = SMSData(cursor.getLong(cursor.getColumnIndex(AppConstants.SMS_DB_KEY_ID)),cursor.getString(cursor.getColumnIndex(AppConstants.SMS_DB_KEY_ADDRESS)),
                        getDateTime(smsTimeStamp.toLong(), EnumConstants.DATE.ordinal), getDateTime(cursor.getString(cursor.getColumnIndex(AppConstants.SMS_DB_KEY_DATE)).toLong(), EnumConstants.TIME.ordinal), cursor.getString(cursor.getColumnIndex(AppConstants.SMS_DB_KEY_BODY)),
                        cursor.getString(cursor.getColumnIndex(AppConstants.SMS_DB_KEY_PERSON)), getTimeDifference(smsTimeStamp.toLong()))

                alSMSList.add(smsData)
            }
        }
    }

    private fun getDateTime(timestamp: Long, dateTime : Int) : String {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.timeInMillis = timestamp * 1000L
        return if(dateTime == EnumConstants.DATE.ordinal) {
            //date
            //DateFormat.format("dd-MM-yyyy", calendar).toString()
            DateFormat.format("dd MMM", calendar).toString()
        } else {
            //time
            DateFormat.format("hh:mm a", calendar).toString()
        }
    }

    fun epochToIso8601(time: Long): String? {
        val format = "yyyy-MM-dd HH:mm:ss"
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(Date(time * 1000))
    }

    private fun getTimeDifference(timeToCompare : Long) : String {
        //hh:mm:ss
        /*return java.lang.String.format(
            "%02d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(timeToCompare),
            TimeUnit.MILLISECONDS.toMinutes(timeToCompare) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(timeToCompare) % TimeUnit.MINUTES.toSeconds(1), Locale.UK
        )*/
        //compareTwoTimeStamps(Timestamp(timeToCompare))
        //getDate(timeToCompare)

        val date = Date(timeToCompare * 1000)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.mmm'Z'", Locale.ENGLISH)
        //println(dateFormat.format(date))
        val cal = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()
        //val datee = cal.time
        val mHour = date.hours
        val mMinute = date.minutes
        //return DateUtils.getRelativeTimeSpanString(date.time).toString()
        return epochToIso8601(timeToCompare).toString()
    }

    fun getDate(timestamp: Long): String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.setTimeInMillis(timestamp * 1000L)
        return DateFormat.format("yyyy-MM-dd hh:mm:ss", calendar).toString()
        //return SimpleDateFormat("yyyy-MM-dd  hh:mm:ss").format(timestamp)
    }



    fun compareTwoTimeStamps(oldTime: Timestamp): Long {
        val timestamp = Timestamp(System.currentTimeMillis())

        val milliseconds1 = oldTime.time * 1000L
        val milliseconds2 = timestamp.time
        val diff = milliseconds2 - milliseconds1
        val diffSeconds = diff / 1000
        val diffMinutes = diff / (60 * 1000)
        val diffHours = diff / (60 * 60 * 1000)
        val diffDays = diff / (24 * 60 * 60 * 1000)
        return diffHours
    }
}